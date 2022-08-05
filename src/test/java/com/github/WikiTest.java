package com.github;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class WikiTest {

    @BeforeAll
    static void configure() {
        Configuration.holdBrowserOpen = true;
         Configuration.browserSize = "1920x1080";
    }

    @Test
    void wikiSearch () {
     open("https://github.com/");
     $("[name = q]").click();
     $("[name = q]").setValue("selenide").pressEnter();
     $$("ul.repo-list li").first().$("a").click();
     $("#wiki-tab").click();
     $(byText("Show 2 more pages…")).click();
     $(byText("Soft assertions")).click();
     $("#js-repo-pjax-container").shouldHave(text("""
             Using JUnit5 extend test class:
             @ExtendWith({SoftAssertsExtension.class})
             class Tests {
               @Test
               void test() {
                 Configuration.assertionMode = SOFT;
                 open("page.html");

                 $("#first").should(visible).click();
                 $("#second").should(visible).click();
               }
             }"""));
    }
}
