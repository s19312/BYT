import java.util.*;

public class Configuration {
    private int interval;
    private int duration;
    private int departure;

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDeparture() {
        return departure;
    }

    public void setDeparture(int departure) {
        this.departure = departure;
    }


    public void load(Properties props) throws ConfigurationException {
        loadInterval(props);
        loadDuration(props);
        loadDeparture(props);
    }

    private void loadInterval(Properties props) throws ConfigurationException {
        String valueString = props.getProperty("interval");
        if (valueString == null) {
            throw new ConfigurationException("monitor interval");
        }
        int value = Integer.parseInt(valueString);
        if (value <= 0) {
            throw new ConfigurationException("monitor interval > 0");
        }
        interval = value;
    }

    private void loadDuration(Properties props) throws ConfigurationException {
        String valueString = props.getProperty("duration");
        if (valueString == null) {
            throw new ConfigurationException("duration");
        }
        int value = Integer.parseInt(valueString);
        if (value <= 0) {
            throw new ConfigurationException("duration > 0");
        }
        if ((value % interval) != 0) {
            throw new ConfigurationException("duration % interval");
        }
        duration = value;
    }

    private void loadDeparture(Properties props) throws ConfigurationException {
        String valueString = props.getProperty("departure");
        if (valueString == null) {
            throw new ConfigurationException("departure offset");
        }
        int value = Integer.parseInt(valueString);
        if (value <= 0) {
            throw new ConfigurationException("departure > 0");
        } else if ((value % interval) != 0) {
            throw new ConfigurationException("departure % interval");
        }
        departure = value;
    }
}
