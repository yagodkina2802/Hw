package Kit;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Data
public class MontyHallParadox {
    private static final int COUNT_SIMULATIONS = 1000;

    private int winsSwitching;
    private int lossesSwitching;
    private int winsNotSwitching;
    private int lossesNotSwitching;
    private Map<Integer, String> resultsMap;

    public MontyHallParadox() {
        this.winsSwitching = 0;
        this.lossesSwitching = 0;
        this.winsNotSwitching = 0;
        this.lossesNotSwitching = 0;
        this.resultsMap = new HashMap<>();
    }

    public void runSimulation() {
        Random random = new Random();
        for (int i = 1; i <= COUNT_SIMULATIONS; i++) {
            int winningDoor = random.nextInt(3);
            int chosenDoor = random.nextInt(3);
            boolean switchDecision = random.nextBoolean();
            int shownDoor;
            do {
                shownDoor = random.nextInt(3);
            } while (shownDoor == winningDoor || shownDoor == chosenDoor);
            if (switchDecision) {
                chosenDoor = 3 - chosenDoor - shownDoor;
                if (chosenDoor == winningDoor) {
                    winsSwitching++;
                    resultsMap.put(i, "Победа (смена выбора)");
                } else {
                    lossesSwitching++;
                    resultsMap.put(i, "Проигрыш (смена выбора)");
                }
            } else {
                if (chosenDoor == winningDoor) {
                    winsNotSwitching++;
                    resultsMap.put(i, "Победа (без смены выбора)");
                } else {
                    lossesNotSwitching++;
                    resultsMap.put(i, "Проигрыш (без смены выбора)");
                }
            }
        }
    }

    public void printResults() {
        System.out.println("===== Статистика моделирования парадокса Монти Холла =====");
        System.out.println("Всего попыток: " + COUNT_SIMULATIONS);
        System.out.println("Выйгрыши со сменой выбора: " + winsSwitching);
        System.out.println("Проигрыши со сменой выбора: " + lossesSwitching);
        System.out.println("Выйгрыши без смены выбора: " + winsNotSwitching);
        System.out.println("Проигрыши без смены выбора: " + lossesNotSwitching);
        System.out.println("=========================================");

        System.out.println("Все результаты моделирования: ");
        resultsMap.forEach((step, result) -> System.out.println("Попытка " + step + ": " + result));
    }
}