package johnsonDanielMinigame;

public class DanielLocker {

	private boolean isBomb;
	private boolean isPerson;
	private boolean isEmpty;
	private boolean isOpen;

	
	public DanielLocker() {
		isBomb = false;
		isPerson = false;
		isEmpty = true;
		isOpen = false;
	}

	public boolean getIsBomb() {
		return isBomb;
	}

	public void setBomb(boolean isBomb) {
		this.isBomb = isBomb;
	}

	public boolean getIsPerson() {
		return isPerson;
	}

	public void setPerson(boolean isPerson) {
		this.isPerson = isPerson;
	}

	public boolean getIsEmpty() {
		return isEmpty;
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}
	
	public boolean getIsOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	
	
	
	
	

}
