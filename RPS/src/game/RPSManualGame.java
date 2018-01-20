package game;

import java.util.ArrayList;
import player.RPSPlayer;
import player.RPSPlayerBase;
import player.RPSType;

/**
 * マニュアルじゃんけんクラス.
 *
 */
public class RPSManualGame {

  /** インスタンス. */
  private static RPSManualGame sRPSManualGame = null;

  /** 結果履歴. */
  private ArrayList<Boolean> mResultList = new ArrayList<>();

  /**
   * コンストラクタ.
   */
  private RPSManualGame() {}

  /**
   * インスタンス生成.
   * @return インスタンス.
   */
  public static RPSManualGame getInstance() {
    if (sRPSManualGame == null) {
      sRPSManualGame = new RPSManualGame();
    }
    return sRPSManualGame;
  }

  /**
   * マニュアルじゃんけん.
   * @return true:継続 false:終了.
   */
  public boolean startManualGame() {
    RPSType manualType;
    RPSType rivalType;
    boolean isSame = false;
    RPSPlayerBase rivalPlayer = new RPSPlayer();
    do {
      // あいこの場合は専用文字表示.
      if (isSame) {
        System.out.println("\nあ～いこ～で。。。\n　1:グー\n　2:チョキ\n　3:パー\n　e:やめる");
      } else {
        System.out.println("\nじゃんけんの手を入力してください\n　1:グー\n　2:チョキ\n　3:パー\n　e:戻る");
      }

      manualType = null;
      while (manualType == null) {

        // マニュアルプレーヤーの手入力.
        String input = ScannerManager.getInstance().getInput();


        // 1,2,3なら終了.
        switch (input) {
          case "1":
            // グー.
            manualType = RPSType.ROCK;
            break;
          case "2":
            // チョキ.
            manualType = RPSType.SCISSORS;
            break;
          case "3":
            // パー.
            manualType = RPSType.PAPER;
            break;
          case "e":
            System.out.println("戻ります\n");
            return false;
          default:
            // その他は再入力させる.
            System.out.println("不正な値が入力されています\n");
            return true;
        }
      }

      // 相手の手を決定する.
      rivalType = rivalPlayer.go();

      if (rivalType == null) {
        // nullの場合は不正.
        System.out.println("不正が発生しました。トップに戻ります");
        return false;
      }

      // あいこかどうかをチェック.
      isSame = manualType == rivalType;

    } while (isSame);


    // 結果表示.
    System.out.println("\n\n\n\n\n-------------------------------------------\n");
    System.out.println("あなた：" + manualType + " vs " + rivalType + " : あいて");

    // 勝者判定.
    JudgmentResult result = RPSGame.isWinnerFirstPlayer(manualType, rivalType);
    mResultList.add(result == JudgmentResult.WIN);

    switch (result) {
      case WIN:
        System.out.println("\nあなたの勝ちです！やったね☆\n");
        break;
      case LOSE:
        System.out.println("\nあなたの負けです。。。残念。。。\n");
        break;
      default:
        System.out.println("\nエラーが発生しました。トップに戻ります\n");
        return false;
    }
    System.out.println("-------------------------------------------");
    int winCount = 0;
    for (boolean isWin : mResultList) {
      if (isWin) {
        winCount++;
      }
    }
    System.out.println("あなたの通算成績 : " + winCount + "勝 " + (mResultList.size() - winCount) + "敗");

    // 履歴表示.
    for (int i = 0; i < mResultList.size(); i += 10) {
      StringBuilder builder = new StringBuilder();
      for (int j = i; j < i + 10 && j < mResultList.size(); j++) {
        builder.append(mResultList.get(j) ? "〇" : "×");
      }
      System.out.println(builder.toString());
    }
    System.out.println("-------------------------------------------\n\n");


    // 相手に結果を通知.
    // 引き分けは通知しない.
    if (result != JudgmentResult.AIKO) {
      // 相手は後手なので、resultは逆.
      rivalPlayer.onResult(result == JudgmentResult.LOSE);
    }

    return true;
  }
}
