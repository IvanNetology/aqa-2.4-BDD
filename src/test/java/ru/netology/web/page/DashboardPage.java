package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
//    private SelenideElement cardFirst = $$("[data-test-id=action-deposit]").get(0);
    private SelenideElement cardSecond = $$("[data-test-id=action-deposit]").get(1);
    private SelenideElement amount = $("[data-test-id=amount] input");
    private SelenideElement from = $("[data-test-id=from] input");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public DashboardPage successTransferCard1ToCard2(DataHelper.CardInfo cardInfo) {
        cardSecond.click();
        amount.setValue(cardInfo.getSumma());
        from.setValue(cardInfo.getNumber());
        transferButton.click();
        return new DashboardPage();
    }
}
