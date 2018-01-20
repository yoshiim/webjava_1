package player;

import java.util.ArrayList;

public class SearchPlayer extends RPSPlayerBase {
  // 前回の自分の手.
  private RPSType lastMyType = null;

  /** 相手の手リスト. */
  private ArrayList<RPSType> rivalTypeList = new ArrayList<>();

  /** 結果待ちフラグ(あいこに使う). */
  private boolean isWaitResult = false;

  @Override
  public RPSType go() {

    if (lastMyType != null && isWaitResult) {
      // 初回以外かつ結果待ち状態の場合は前回はあいこだったと判断.
      // 前回の自分の手と同じ手をリストに追加する.
      rivalTypeList.add(lastMyType);
      // 待ち状態は解除.
      isWaitResult = false;
    }

    if (rivalTypeList.size() < 2) {
      // 相手の手が2回未満の場合は意味ないのでランダムで出す.
      lastMyType = new RPSPlayer().go();
    } else {
      // 2回以上の場合は計算する.
      int rock = 0;
      int scissors = 0;
      int paper = 0;
      // 相手の手の半分より後の並びが存在するかをチェックしていく.
      for (int i = rivalTypeList.size() / 2; i < rivalTypeList.size(); i++) {
        // 相手の手全体ループ.
        int iCount = rivalTypeList.size() - i;
        for (int j = 0; j + iCount < rivalTypeList.size(); j++) {
          // 並びチェックループ
          boolean isSame = true;
          for (int k = 0;  k < iCount; k++) {
            // 異なるものがあったらループ終了.
            if (rivalTypeList.get(i + k) != rivalTypeList.get(j + k)) {
              isSame = false;
              break;
            }
          }
          if (isSame) {
            int a = j + iCount - 1;
            if (a + 1 < rivalTypeList.size()) {
              switch (rivalTypeList.get(a + 1)) {
                case PAPER:
                  scissors++;
                  break;
                case ROCK:
                  paper++;
                  break;
                default:
                  rock++;
                  break;
              }
            }
          }
        }
        if (paper + rock + scissors != 0) {
          break;
        }
      }

      if (paper + rock + scissors != 0) {
        RPSType type = RPSType.PAPER;
        int max = paper;
        if (max < rock) {
          type = RPSType.ROCK;
          max = rock;
        }
        if (max < scissors) {
          type = RPSType.SCISSORS;
        }
        lastMyType = type;
      } else {
        // 相手の手が2回未満の場合は意味ないのでランダムで出す.
        lastMyType = new RPSPlayer().go();
      }
    }

    // 結果待ち状態にする.
    isWaitResult = true;
    return lastMyType;
  }

  @Override
  public void onResult(boolean isWinner) {

    // 結果待ちを解除.
    isWaitResult = false;

    // 勝ち負けから相手の手を判断.
    RPSType lastRivalType;
    if (isWinner) {
      switch (lastMyType) {
        case PAPER:
          lastRivalType = RPSType.ROCK;
          break;
        case ROCK:
          lastRivalType = RPSType.SCISSORS;
          break;
        default:
          lastRivalType = RPSType.PAPER;
          break;
      }
    } else {
      switch (lastMyType) {
        case PAPER:
          lastRivalType = RPSType.SCISSORS;
          break;
        case ROCK:
          lastRivalType = RPSType.PAPER;
          break;
        default:
          lastRivalType = RPSType.ROCK;
          break;
      }
    }

    // 相手の手を保存.
    rivalTypeList.add(lastRivalType);
  }
}
