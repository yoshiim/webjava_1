package player;

/**
 * ランダムじゃんけんプレーヤークラス.
 *
 */

public class RPSPlayer extends RPSPlayerBase {
  @Override
  public RPSType go() {
    int random = (int)(Math.random() * 3);

    RPSType type = null;
    switch (random) {
      case 0:
        type = RPSType.ROCK;
        break;
      case 1:
        type = RPSType.SCISSORS;
        break;
      case 2:
        type = RPSType.PAPER;
        break;
      default:
        break;
    }

    return type;
  }

  @Override
  public void onResult(boolean isWinner) {
    // TODO 自動生成されたメソッド・スタブ

  }
}
