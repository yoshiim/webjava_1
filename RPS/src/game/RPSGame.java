package game;

import player.RPSType;

/**
 * じゃんけんゲームメインクラス.
 *
 */
public class RPSGame {

  /**
   * メインクラス.
   * @param args コマンドラインからの引数.
   */
  public static void main(String[] args) {

    ScannerManager scannerManager = ScannerManager.getInstance();
    // 正常な値が入力されるまでループ.
    boolean isContinue = true;
    while (isContinue) {

      System.out.println("じゃんけんゲームを開始します。何をしますか？\n　m：マニュアル\n　a：オート\n　e：終了");

      // 文字を入力.
      String input = scannerManager.getInput();
      if (input == null) {
        System.out.println("\nエラーが発生しました。終了します。");
        break;
      }

      switch (input) {
        // マニュアル.
        case "m":
          manual();
          break;
        // オート.
        case "a":
          auto();
          break;
        // 終了.
        case "e":
          isContinue = false;
          System.out.println("じゃんけんゲームを終了します");
          break;
        default:
          // それ以外はループ.
          break;
      }
    }
    // スキャナーを終了.
    scannerManager.finish();
  }

  /**
   * マニュアルじゃんけん.
   */
  public static void manual() {
    // falseが返ってくるまでループ.
    RPSManualGame rpsManualGame = RPSManualGame.getInstance();
    while (rpsManualGame.startManualGame()) {};
  }

  /**
   * 自動じゃんけん.
   */
  public static void auto() {
    // falseが返ってくるまでループ.
    RPSAutoGame rpsAutoGamee = RPSAutoGame.getInstance();
    while (rpsAutoGamee.startAutoGame()) {};
  }

  /**
   * 勝者が1番目のプレーヤーかどうか.
   * @param first 1番目のプレーヤーの手.
   * @param second 2番目のプレーヤーの手.
   * @return true:1番目の勝ち false:2番目の勝ち.
   */
  public static JudgmentResult isWinnerFirstPlayer(final RPSType first, final RPSType second) {

    JudgmentResult result = null;
    switch (first) {
      case ROCK:
        switch (second) {
          case ROCK:
            result = JudgmentResult.AIKO;
            break;
          case SCISSORS:
            result = JudgmentResult.WIN;
            break;
          case PAPER:
            result = JudgmentResult.LOSE;
            break;
          default:
            break;
        }
        break;
      case SCISSORS:
        switch (second) {
          case ROCK:
            result = JudgmentResult.LOSE;
            break;
          case SCISSORS:
            result = JudgmentResult.AIKO;
            break;
          case PAPER:
            result = JudgmentResult.WIN;
            break;
          default:
            break;
        }
        break;
      case PAPER:
        switch (second) {
          case ROCK:
            result = JudgmentResult.WIN;
            break;
          case SCISSORS:
            result = JudgmentResult.LOSE;
            break;
          case PAPER:
            result = JudgmentResult.AIKO;
            break;
          default:
            break;
        }
        break;
      default:
        break;
    }
    return result;
  }

}
