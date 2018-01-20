package player;

/**
 * じゃんけんプレイヤーベースクラス.
 *
 */
public abstract class RPSPlayerBase {
  /**
   * じゃんけん手作成.
   * @return 手.
   */
  public abstract RPSType go();

  /**
   * 結果通知.
   * @param isWinner 勝ったかどうか.
   */
  public abstract void onResult(boolean isWinner);
}
