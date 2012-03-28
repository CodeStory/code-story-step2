var Browser = require("zombie");
var assert = require("assert");

assert.exists = function(selector) {
	assert.ok(browser.query(selector));
}

assert.notExists = function(selector) {
	assert.ok(!browser.query(selector));
}

port = process.argv[2]
console.log(port);
browser = new Browser()
browser.visit("http://localhost:" + port + "/", function() {
	should_have_correct_title();
	should_show_aged_brie_image_when_aged_brie_link_is_clicked();
});

should_have_correct_title = function() {
    assert.equal(browser.text("title"), "Gilded Rose Inn");	
}

should_show_aged_brie_image_when_aged_brie_link_is_clicked = function() {
    assert.notExists(".fade.in img[src*='Aged Brie.jpg']");
	
	browser.clickLink("Aged Brie", function() {
		assert.exists(".fade.in img[src*='Aged Brie.jpg']");
	})
}
