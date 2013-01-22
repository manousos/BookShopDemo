using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using DAO;

namespace BookShopDemo.Controllers
{
    public class PublisherController : Controller
    {
        //
        // GET: /Suspense/

        public ActionResult Index()
        {
            var db = new PublisherDataContext();
            var susp = db.Publisher.OrderByDescending(x => x.ID);

            return View(susp);
        }

    }
}
