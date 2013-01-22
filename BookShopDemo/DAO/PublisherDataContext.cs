using System.Data.Entity;
using DAO.Entities;

namespace DAO
{
    public class PublisherDataContext : DbContext
    {
        public DbSet<Publisher> Publisher { get; set; }

        public PublisherDataContext()
        {
#if(DEBUG)
            var initializer = new DropCreateDatabaseIfModelChanges<PublisherDataContext>();
            Database.SetInitializer(initializer);
#endif
        }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Book>()
                .HasRequired(x => x.Publisher)
                .WithMany()
                .WillCascadeOnDelete(false);
        }
    }
}
