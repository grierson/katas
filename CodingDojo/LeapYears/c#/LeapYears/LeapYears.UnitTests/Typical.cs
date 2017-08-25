using System;
using Xunit;

namespace LeapYears.UnitTests
{
    public class Typical
    {
        [Fact]
        public void Year_2001_Not_Leap_Year()
        {
            var x = new LeapYearChecker();
            var f = x.IsLeapYear(2001);
            Assert.False(f);
        }

        [Fact]
        public void Year_1996_Leap_Year()
        {
            var x = new LeapYearChecker();
            var f = x.IsLeapYear(1996);
            Assert.True(f);
        }
    }
}
