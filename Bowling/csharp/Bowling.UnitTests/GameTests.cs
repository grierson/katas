using System;
using Xunit;

namespace Bowling.UnitTests
{
    public class GameTests : IDisposable
    {
        Game game;

        public GameTests()
        {
            game = new Game();
        }

        public void Dispose()
        {
            game = new Game();
        }


        // Start Helpers
        private void rollMany(int frames, int pins)
        {
            for (int i = 0; i < frames; i++)
                game.roll(pins);
        }

        public void rollSpare()
        {
            game.roll(5);
            game.roll(5);
        }


        private void rollStrike()
        {
            game.roll(10);
        }
        // End Helpers

        [Fact]
        public void gutter_game_test()
        {
            rollMany(10, 0);

            Assert.Equal(0, game.score());
        }

        [Fact]
        public void all_ones_test()
        {
            rollMany(10, 1);

            Assert.Equal(10, game.score());
        }

        [Fact]
        public void one_spare_test()
        {
            rollSpare();
            game.roll(3);
            rollMany(17, 0);

            Assert.Equal(16, game.score());
        }

        [Fact]
        public void one_strike_test()
        {
            rollStrike();
            game.roll(3);
            game.roll(4);
            rollMany(16, 0);

            Assert.Equal(24, game.score());
        }

        [Fact]
        public void perfect_game_test()
        {
            rollMany(12, 10);
            Assert.Equal(300, game.score());
        }
    }
}
