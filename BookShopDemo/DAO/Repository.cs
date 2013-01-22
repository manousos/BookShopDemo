using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Diagnostics.Contracts;
using System.Linq;
using System.Linq.Expressions;
using DAO.Entities;

namespace DAO
{
    public class Repository : IRepository
    {
        private readonly DbContext _context;
        private readonly bool _isSharedContext;

        public Repository(DbContext context, bool isSharedContext = true)
        {
            Contract.Requires(context != null);

            _context = context;
            _isSharedContext = isSharedContext;
        }

        #region IRepository Members

        public void Add<TModel>(TModel instance) where TModel : class, Entities.IEntity
        {
            Contract.Requires(instance != null);

            _context.Set<TModel>().Add(instance);

            if (_isSharedContext == false)
                _context.SaveChanges();
        }

        public void Add<TModel>(IEnumerable<TModel> instances) where TModel : class, Entities.IEntity
        {
            Contract.Requires(instances != null);

            foreach (var instance in instances)
            {
                Add(instance);
            }
        }

        public IQueryable<TModel> All<TModel>(params string[] includePaths) where TModel : class, Entities.IEntity
        {
            return Query<TModel>(x => true, includePaths);
        }

        public void Delete<TModel>(object key) where TModel : class, Entities.IEntity
        {
            Contract.Requires(key != null);

            var instance = Single<TModel>(key);
            Delete(instance);
        }

        public void Delete<TModel>(TModel instance) where TModel : class, Entities.IEntity
        {
            Contract.Requires(instance != null);

            if (instance != null)
                _context.Set<TModel>().Remove(instance);
        }

        public void Delete<TModel>(System.Linq.Expressions.Expression<Func<TModel, bool>> predicate) where TModel : class, Entities.IEntity
        {
            Contract.Requires(predicate != null);

            TModel entity = Single(predicate);
            Delete(entity);
        }

        public TModel Single<TModel>(object key) where TModel : class, Entities.IEntity
        {
            Contract.Requires(key != null);

            var instance = _context.Set<TModel>().Find(key);
            return instance;
        }

        public TModel Single<TModel>(System.Linq.Expressions.Expression<Func<TModel, bool>> predicate, params string[] includePaths) where TModel : class, Entities.IEntity
        {
            Contract.Requires(predicate != null);

            var instance = GetSetWithIncludedPaths<TModel>(includePaths).SingleOrDefault(predicate);
            return instance;
        }

        public IQueryable<TModel> Query<TModel>(System.Linq.Expressions.Expression<Func<TModel, bool>> predicate, params string[] includePaths) where TModel : class, Entities.IEntity
        {
            Contract.Requires(predicate != null);

            var items = GetSetWithIncludedPaths<TModel>(includePaths);

            if (predicate != null)
                return items.Where(predicate);

            return items;
        }

        #endregion

        #region IDisposable Members

        public void Dispose()
        {
            throw new NotImplementedException();
        }

        #endregion

        private DbQuery<TModel> GetSetWithIncludedPaths<TModel>(IEnumerable<string> includedPaths) where TModel : class, IEntity
        {
            DbQuery<TModel> items = _context.Set<TModel>();

            foreach (var path in includedPaths ?? Enumerable.Empty<string>())
            {
                items = items.Include(path);
            }

            return items;
        }
    }
}
