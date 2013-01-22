using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Web.Mvc;
using System.Linq;


namespace Models
{
    public class SearchBook
    {
        [DisplayName("Κωδικός βιβλίου")]
        public string isbn { get; set; }
             
    }
}
