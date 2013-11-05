package clueGame;

public class Card {
	
	public enum CardType {
		PERSON, WEAPON, ROOM
	};
	
	private String name;
	private CardType type;
	
	public String getName() {
		return name;
	}
	public Card(String name, CardType type) {
		super();
		this.name = name;
		this.type = type;
	}
	
	public Card() {
		// TODO Auto-generated constructor stub
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public CardType getType() {
		return type;
	}
	public void setType(CardType type) {
		this.type = type;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Card [name=" + name + ", type=" + type + "]";
	}

	
	
	
	
}
