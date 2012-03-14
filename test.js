var Browser = require("zombie");
var assert = require("assert");

assert.exists = function(selector) {
	assert.ok(browser.query(selector));
}

assert.notExists = function(selector) {
	assert.ok(!browser.query(selector));
}

browser = new Browser()
browser.visit("http://localhost:8080/", function() {
    assert.ok(browser.success);
    assert.equal(browser.text("title"), "Gilded Rose Inn");

    assert.exists(".fade img[src='/web/img/Aged Brie.jpg']");
    assert.notExists(".fade.in img[src='/web/img/Aged Brie.jpg']");
	
	browser.clickLink("Aged Brie", function() {
		assert.exists(".fade.in img[src='/web/img/Aged Brie.jpg']");
	})
});