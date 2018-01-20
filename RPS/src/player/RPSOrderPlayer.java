package player;

/**
 * グーチョキパーを順番に出す.
 *
 */
public class RPSOrderPlayer extends RPSPlayerBase {
  // 前回の自分の手.
  private RPSType lastMyType = RPSType.PAPER;

  @Override
  public RPSType go() {
    switch (lastMyType) {
      case ROCK:
        lastMyType = RPSType.SCISSORS;
        break;
      case SCISSORS:
        lastMyType = RPSType.PAPER;
        break;
      default:
        lastMyType = RPSType.ROCK;
    }
    return lastMyType;
  }

  @Override
  public void onResult(boolean isWinner) {
    // TODO 自動生成されたメソッド・スタブ

  }

}
