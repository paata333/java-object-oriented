public class MLSTest {
    public static void main(String[] args) {
        Device device1 = new Device();
        Device device2 = new Device();

        device1.setName("ლეპტოპი Acer Swift");
        device1.setPrice(1499);
        device1.setDate("2026-12-31");  // Fixed date format

        device2.setName("iPhone 15 Pro");
        device2.setPrice(1299);  // Using price instead of author
        device2.setDate("2027-06-15");  // Using date instead of year

        DMS dms = new DMS();
        dms.addDevice(device1);  // Fixed method name
        dms.addDevice(device2);

        dms.printInfo();  // Fixed instance method call
    }
}
