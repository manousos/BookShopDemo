using System;
using System.ComponentModel.DataAnnotations;

namespace DAO.CustomValidator
{
    public class DateTimeValidationAttribute : ValidationAttribute
    {
        private const string _defaultErrorMessage = "Με αποδεκτή ημερομηνία";
        private string _basePropertyName;

        public override string FormatErrorMessage(string name)
        {
            return string.Format(_defaultErrorMessage, name, _basePropertyName);
        }

        public override bool IsValid(object value)
        {
            if (value != null)
                if (!((DateTime)value >= DateTime.Now))
                    return false;

            return true;
        }
    }
}
