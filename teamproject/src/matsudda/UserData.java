package matsudda;

public class UserData {
	private Card card;
	protected int nMoney;

	public UserData() {
		card = new Card();
		nMoney = 20000000;
	} // UserData()

	public UserData(int userCard1, int userCard2) {
		card = new Card(userCard1, userCard2);
		nMoney = 20000000;
	} // UserData()

	public void setMoney(int money) {
		nMoney = money;
	} // setMoney()

	public void setCard() {
		card.setCard();
	} // setCard()

	public void setCard(int userCard1, int userCard2) {
		card.setCard(userCard1, userCard2);
	} // setCard()

	public int getCard1() {
		return card.getCard1();
	} // getCard1()

	public int getCard2() {
		return card.getCard2();
	} // getCard2()

	public int getPriority() {
		return card.getPriority();
	} // getPriority()

} // UserData class
