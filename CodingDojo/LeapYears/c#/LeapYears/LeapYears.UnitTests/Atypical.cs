using System;
using Xunit;

namespace LeapYears.UnitTests
{
    public class Atypical
    {
        [Fact]
        public void Year_1900_Not_Leap_Year()
        {
            var x = new LeapYearChecker();
            var f = x.IsLeapYear(1900);
            Assert.False(f);
        }

        [Fact]
        public void Year_2000_Leap_Year()
        {
            var x = new LeapYearChecker();
            var f = x.IsLeapYear(2000);
            Assert.True(f);
        }
    }
}
