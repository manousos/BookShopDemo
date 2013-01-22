using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.Objects.DataClasses;


namespace Models
{
    public class SearchResult
    {
        public int AN { get; set; }
        public int No { get; set; }
        public String CustAcnt { get; set; }
        public String SapAcntName { get; set; }
        public String SapAcntNo { get; set; }
        public String SapRefNo { get; set; }
        public String EDPL { get; set; }
        public Decimal Amount { get; set; }
        public String Status { get; set; }
        public int StatusID { get; set; }
        public String BranchNotes { get; set; }
        public DateTime? WishDate { get; set; }
        public IEnumerable<string> Files { get; set; }

        public SearchResult() { }
    }
}
