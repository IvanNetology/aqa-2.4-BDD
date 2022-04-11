package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.web.data.DataHelper;

import java.util.Objects;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private ElementsCollection cardButton = $$("[data-test-id=action-deposit]");
    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public TransferPage shouldTransferCard1ToCard2() {
        cardButton.last().click();
        return new TransferPage();
    }

    public TransferPage shouldTransferCard2ToCard1() {
        cardButton.first().click();
        return new TransferPage();
    }

    public int getCardBalance(String id) {
        for (SelenideElement card : cards) {
            if (Objects.equals(card.getDomAttribute("data-test-id"), id)) {
                val text = card.text();
                return extractBalance(text);
            }
        }
        return 0;
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}
