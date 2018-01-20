package game;

import player.SearchPlayer;
import player.RPSOrderPlayer;
import player.RPSPlayer;
import player.RPSPlayerBase;
import player.RPSType;
import player.RockPlayer;

/**
 * 自動じゃんけんゲーム.
 *
 */
public class RPSAutoGame {

  /** インスタンス. */
  private static RPSAutoGame sRPSAutoGame = null;
  /** 試合数. */
  private static final int MAX_GAME_COUNT = 50;

  /**
   * コンストラクタ.
   */
  private RPSAutoGame() {}

  /**
   * インスタンス生成.
   * @return インスタンス.
   */
  public static RPSAutoGame getInstance() {
    if (sRPSAutoGame == null) {
      sRPSAutoGame = new RPSAutoGame();
    }
    return sRPSAutoGame;
  }

  /**
   * 自動ゲーム開始.
   * @return true:継続 false:終了.
   */
  public boolean startAutoGame() {
    // 先手プレーヤー.
    RPSPlayerBase firstPlayer = null;
    while (firstPlayer == null) {
      System.out.println("\n先手プレイヤーを選択してください。");
      firstPlayer = selectPlayer();
      if (firstPlayer == null) {
        System.out.println("\n正確に選んでください。\n");
      }
    }

    // 後手プレーヤー.
    RPSPlayerBase secondPlayer = null;
    while (secondPlayer == null) {
      System.out.println("\n後手プレイヤーを選択してください。");
      secondPlayer = selectPlayer();
      if (secondPlayer == null) {
        System.out.println("\n正確に選んでください。\n");
      }
    }

    // 規定の試合回数試合を行う.
    int firstWinTimes = 0;
    for (int i = 0; i < MAX_GAME_COUNT;) {
      RPSType firstType = firstPlayer.go();
      RPSType secondType = secondPlayer.go();

      JudgmentResult result = RPSGame.isWinnerFirstPlayer(firstType, secondType);

      switch (result) {
        case WIN:
          i++;
          firstWinTimes++;
          // 結果通知.
          firstPlayer.onResult(true);
          secondPlayer.onResult(false);
          break;
        case LOSE:
          i++;
          // 結果通知.
          firstPlayer.onResult(false);
          secondPlayer.onResult(true);
          break;
        default:
          // あいこはカウントしない.
          break;
      }

      System.out.println(i + " 通算：" + firstWinTimes + " 結果：" + result);
    }

    System.out.println("\n---------------------------\n");
    System.out.println("対戦結果");
    System.out.println("先手：" + firstWinTimes + "勝　vs　後手：" + (MAX_GAME_COUNT - firstWinTimes) + "勝");
    System.out.println("\n---------------------------\n");
    while (true) {
      System.out.println("\nもういちど対戦を行いますか？\n　1：もう一度対戦する\n　2：やめる\n");
      String input = ScannerManager.getInstance().getInput();
      switch (input) {
        case "1":
          return true;
        case "2":
          return false;
        default:
          System.out.println("指定された文字以外を入力しないでください。\n");
          break;
      }
    }
  }

  /**
   * プレイヤー選択.
   * @return 選択されたプレイヤー.
   */
  private RPSPlayerBase selectPlayer() {
    
    // プレイヤーの種類を表示.
    System.out.println("　1：ランダム");
    System.out.println("　2：グー");
    System.out.println("　3：順番");
    System.out.println("　4：記憶");
    
    // プレイヤー選択.
    String input = ScannerManager.getInstance().getInput();
    RPSPlayerBase player = null;
    switch (input) {
      // ランダム.
      case "1":
        player = new RPSPlayer();
        break;
      // グー.
      case "2":
        player = new RockPlayer();
        break;
      // 順番
      case "3":
        player = new RPSOrderPlayer();
        break;
      // テスト.
      case "4":
        player = new SearchPlayer();
        break;
      // その他.
      default:
        break;
    }

    return player;
  }
}
