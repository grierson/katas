using System;

namespace LeapYears
{
    /*
     * Leap year (div by 4) but not 100 unless also 400
     */
    public class LeapYearChecker
    {
        public bool IsLeapYear(int year)
        {
            if (year < 1582)
            {
                if (year % 4 == 0)
                {
                    return true;
                }
            }

            if (year % 400 == 0)
            {
                return true;
            }

            if (year % 4 == 0 && year % 100 != 0) {
                return true;
            }

            return false;
        }
    }
}
