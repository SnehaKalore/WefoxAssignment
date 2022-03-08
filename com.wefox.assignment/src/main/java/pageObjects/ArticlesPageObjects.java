package pageObjects;

public class ArticlesPageObjects {

	String continueBtn_xpath = "//*[contains(@data-test,'age-gate-grown-up-cta')]";
	String cookieAcceptBtn = "//*[contains(@data-test,'cookie-accept-all')]";
	String toBuyBtn_xpath = "//*[@id='blt51f52bea34c3fb01_menubutton']";
	String agesBtn_xpath = "//*[@id='bltf40e5ca6477988b4_submenubutton']";
	String ageRange_xpath = "//*[contains(@href, 'age-4-plus') and @data-analytics-title='4+']";
	String priceRangeChkBox_xpath = "//*[contains(@data-test, 'prices-accordion-content-child')]//li[1]//input";
	String priceRangeChkBox2_xpath = "//*[contains(@data-test, 'prices-accordion-content-child')]//li[1]//div[@data-test='checkbox']";
	String pricesOfAllListedItems_xpathList = "//*[contains(@class, 'Productsstyles')]//div[contains(@class, 'ProductLeafSharedstyles__DetailsRow')]//span[@data-test='product-price']";
	String showAllProductsBtn_xpath = "//*[@data-test='pagination-show-all']";
	String totalNoOfProduct_xpath = "//*[@data-test='listing-show-of']";
	String totalNoOfDataLeaf_xpathList = "//*[@data-test='product-leaf']";
	String firstProductName_xpath = "//*[contains(@class, 'Productsstyles')]//div[@data-test='product-leaf'][$]//div[contains(@class, 'ProductLeafSharedstyles__DetailsRow')]//a[@data-test='product-leaf-title-link']//span";
	String firstProductPrice_xpath = "//*[contains(@class, 'Productsstyles')]//div[@data-test='product-leaf'][$]//div[contains(@class, 'ProductLeafSharedstyles__DetailsRow')]//span[@data-test='product-price']";
	String addToCartBtn_xpath = "//*[contains(text(), 'Diversión Oceánica Creativa')]//parent::h2//..//..//..//button";
	String viewMyBagBtn_xpath = "//a[@data-test='view-my-bag']";
	String checkoutBtn_xpath = "//*[contains(@href, 'checkout')]";
	String productName_xpath = "//*[@data-test='order-details-accordion-content-child']//span[@data-test='product-title']/span";
	String productPrice_xpath = "//*[@data-test='order-details-accordion-content-child']//span[@data-test='product-price']";
	String productQuantity_xpath = "//*[@data-test='order-details-accordion-content-child']//p";
	String additionalTax_xpath = "//span[text()='Envío estándar']//parent::div//span//span";
	String totalAmount_xpath = "//span[text()='Importe total']//parent::div//span//span";
	String backToTop_xpath = "//*[@data-test='listing-back-to-top']";
	
	public String getBackToTop_xpath() {
		return backToTop_xpath;
	}
	public String getContinueBtn_xpath() {
		return continueBtn_xpath;
	}
	public String getCookieAcceptBtn() {
		return cookieAcceptBtn;
	}
	public String getToBuyBtn_xpath() {
		return toBuyBtn_xpath;
	}
	public String getAgesBtn_xpath() {
		return agesBtn_xpath;
	}
	public String getAgeRange_xpath() {
		return ageRange_xpath;
	}
	public String getPriceRangeChkBox_xpath() {
		return priceRangeChkBox_xpath;
	}
	public String getPriceRangeChkBox2_xpath() {
		return priceRangeChkBox2_xpath;
	}
	public String getPricesOfAllListedItems_xpathList() {
		return pricesOfAllListedItems_xpathList;
	}
	public String getShowAllProductsBtn_xpath() {
		return showAllProductsBtn_xpath;
	}
	public String getTotalNoOfProduct_xpath() {
		return totalNoOfProduct_xpath;
	}
	public String getTotalNoOfDataLeaf_xpathList() {
		return totalNoOfDataLeaf_xpathList;
	}
	public String getFirstProductName_xpath() {
		return firstProductName_xpath;
	}
	public String getFirstProductPrice_xpath() {
		return firstProductPrice_xpath;
	}
	public String getAddToCartBtn_xpath() {
		return addToCartBtn_xpath;
	}
	public String getViewMyBagBtn_xpath() {
		return viewMyBagBtn_xpath;
	}
	public String getCheckoutBtn_xpath() {
		return checkoutBtn_xpath;
	}
	public String getProductName_xpath() {
		return productName_xpath;
	}
	public String getProductPrice_xpath() {
		return productPrice_xpath;
	}
	public String getProductQuantity_xpath() {
		return productQuantity_xpath;
	}
	public String getAdditionalTax_xpath() {
		return additionalTax_xpath;
	}
	public String getTotalAmount_xpath() {
		return totalAmount_xpath;
	}	
}
