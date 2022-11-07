package baseball.controller;

import baseball.model.Score;
import baseball.service.BaseBallService;
import baseball.view.InputView;
import baseball.view.OutputView;
import camp.nextstep.edu.missionutils.Console;

import java.util.Objects;

public class BaseBallController {

    private final BaseBallService baseBallService;

    public BaseBallController(BaseBallService baseBallService) {
        this.baseBallService = baseBallService;
    }

    public void run() {
        baseBallService.init();
        InputView.printStartMessage();
        gameStart();
    }

    private void gameStart() {
        playGameUntilEnd(new Score(0, 0));
        quitOrContinueGame();
    }

    private void playGameUntilEnd(Score score) {
        while (canContinueGame(score)) {
            InputView.printInputMessage();
            String input = Console.readLine();
            score = baseBallService.inputAndCompareAnswer(input);
            OutputView.printResultMessage(score);
        }
    }

    private static boolean canContinueGame(Score score) {
        return Objects.isNull(score) || score.canContinue();
    }

    private void quitOrContinueGame() {
        OutputView.printQuitMessage();
        InputView.printChoiceMessage();
    }
}
