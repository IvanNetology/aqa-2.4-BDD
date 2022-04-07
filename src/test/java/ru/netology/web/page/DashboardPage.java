package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private ElementsCollection cardButton = $$("[data-test-id=action-deposit]");
    private SelenideElement amount = $("[data-test-id=amount] input");
    private SelenideElement from = $("[data-test-id=from] input");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public DashboardPage successTransferCard1ToCard2(DataHelper.CardInfo cardInfo) {
        cardButton.last().click();
        amount.setValue(cardInfo.getSumma());
        from.setValue(cardInfo.getNumber());
        transferButton.click();
        return new DashboardPage();
    }

    public DashboardPage failureTransferCard1ToCard2(DataHelper.CardInfo cardInfo) {
        cardButton.last().click();
        amount.setValue(cardInfo.getSumma());
        from.setValue(cardInfo.getNumber());
        transferButton.click();
        return new DashboardPage();
    }

    public int getCardBalance(String id) {
        for (SelenideElement card : cards) {
            if (card.toString().contains(id)) {
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
