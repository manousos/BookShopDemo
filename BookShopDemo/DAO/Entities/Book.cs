using System;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using DAO.CustomValidator;
using System.Diagnostics.Contracts;

namespace DAO.Entities
{
    [MetadataType(typeof(BookMetadata))]
    public class Book : Entity<Guid>, IEquatable<Book>
    {
        public virtual Publisher Publisher { get; private set; }
        public int PUBLISHER_CODE { get; set; }
        public Double PRICE { get; private set; }
        public String ISBN { get; private set; }
        public String TITLE { get; private set; }

        public Book() { }

        public Book(Publisher publisher, int publisher_code, Double price, string isbn, string title)
        {
            Contract.Requires(publisher != null);

            PUBLISHER_CODE = publisher_code;
            PRICE = price;
            ISBN = isbn;
            TITLE = title;
        }

        public override int GetHashCode()
        {
            return Id.GetHashCode();
        }


        #region IEquatable<Book> Members

        public bool Equals(Book other)
        {
            if (ReferenceEquals(null, other)) return false;
            if (ReferenceEquals(this, other)) return true;
            return other.Id.Equals(Id);
        }

        #endregion
    }

    public class BookMetadata
    {
        [Required]
        [DisplayName("ISBN")]
        public int ISBN { get; set; }
        // [Required(ErrorMessage="A name is required")]
        [DisplayName("ΤΙΜΗ")]
        [DisplayFormat(DataFormatString = "{0:N}")]
        public Double PRICE { get; set; }
        [Required]
        [MinLength(5, ErrorMessage = "Ελάχιστο μήκος 5 χαρακτήρες")]
        [MaxLength(500, ErrorMessage = "Ο ΤΙΤΛΟΣ δεν μπορει να υπερβαίνουν τους 500 χαρακτήρες.")]
        [DisplayName("ΤΙΤΛΟΣ")]
        public String TITLE { get; set; }

    }
}
