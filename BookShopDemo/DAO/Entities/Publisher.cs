using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel;
using System.Collections.Generic;
using System.Collections.ObjectModel;

namespace DAO.Entities
{
    [MetadataType(typeof(PublisherMetadata))]
    public class Publisher : Entity<Guid>
    {
        public int ID { get; private set; }
        public string NAME { get; private set; }
        public string ADDRESS { get; private set; }
        public string EMAIL { get; private set; }
        public virtual ICollection<Book> Books { get; set; }

        public Publisher()
        {
            Books = new Collection<Book>();
        }

        public Publisher(Int32 id, String name, String address, string email)
        {
            ID = id;
            NAME = name;
            ADDRESS = address;
            EMAIL = email;
        }

    }

    public class PublisherMetadata
    {
        [Required]
        [DisplayName("ΟΝΟΜΑ")]
        public Int32 NAME { get; set; }
        [DisplayName("ΛΟΓΑΡΙΑΣΜΟΣ")]
        [Required]
        public String ADDRESS { get; set; }
        [Required]
        [DataType(DataType.EmailAddress)]
        public string EMAIL { get; set; }
    }
}
