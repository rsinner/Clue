
public class Settings {
	public enum Setting {
		OFF("---"), LOW("--+"), MEDIUM("-++"), HIGH("+++");
		private String s;
		Setting(String s) {
			this.s = s;
		}
		public String toString() {
			return s;
		}
	}
}
