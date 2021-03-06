package net.thucydides.showcase.jbehave.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.util.Inflector;
import net.thucydides.showcase.jbehave.steps.serenity.BuyerSteps;

import java.text.ParseException;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchScenarioSteps {
    @Steps
    BuyerSteps buyer;

    @Given("I want to buy (.*)")
    public void buyerWantsToBuy(String article) {
        Serenity.takeScreenshot();
        buyer.opens_home_page();
    }

    @When("I search for '(.*)'")
    public void searchByKeyword(String keyword) {
        Serenity.takeScreenshot();
        buyer.searches_by_keyword(keyword);
    }

    @Then("I should see only articles related to '(.*)'")
    public void resultsForACategoryAndKeywordInARegion(String keyword) throws ParseException {
        buyer.should_see_results_summary_containing(keyword);
    }

    @Given("I want to see articles from a particular shop")
    public void givenIWantToSeeArticlesFromAParticularShop() {
        buyer.opens_home_page();
    }

    @When("I search by shop for '(.*)'")
    public void whenISearchByShopFor(String shopName) {
        buyer.searches_for_shop_called(shopName);
    }

    @Then("I should find (\\d+) (?:shop|shops) called '(.*)'")
    public void thenIShouldFindShopsCall(int count, String shopName) {
        String expectedMessage = String.format("%d %s found for %s", count, pluralized(count,"shop"),shopName);
        buyer.should_see_shop_search_result_summary_of(expectedMessage);
    }

    private String pluralized(int count, String word) {
        return Inflector.getInstance().pluralize(word, count);

    }
}


