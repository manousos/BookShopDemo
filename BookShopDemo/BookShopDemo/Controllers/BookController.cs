using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

using DAO;
using Models;
using DAO.Entities;

namespace BookShopDemo.Controllers
{
    public class BookController : Controller
    {

        private readonly IRepository _repository;

        public BookController(IRepository repository)
        {
            _repository = repository;
        }

        //
        // GET: /Remind/

        public ActionResult Index(SearchBook criteria)
        {
            IQueryable<Book> results;

            results = _repository.Query<Book>(q => q.ISBN == criteria.isbn);

            return View(results);
        }

    }
}
