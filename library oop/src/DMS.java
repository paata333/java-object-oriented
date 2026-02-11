import java.util.*;

public class DMS {
    private List<Device> devices = new ArrayList<>();  // 'devices' for multiple Device objects

    public void addDevice(Device device) {  // Class 'Device', object 'device'
        this.devices.add(device);
    }

    public void removeDevice(Device device) {  // Consistent naming
        this.devices.remove(device);
    } // შევამაწმო და გავაუმჯობესო რემოვის კოდი გუუგლ კლასრუმის და ცჰატ ჯიპიტის დახმარებით

    public void printInfo() {
        for (Device device : devices) {  // Use 'device' for each object in 'devices' list
            System.out.println("Device Name: " + device.getName() +
                    "; Price: " + device.getPrice() +
                    "; Date: " + device.getDate());
        }
    }
}
