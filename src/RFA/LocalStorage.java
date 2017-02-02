package RFA;

import java.io.*;
import java.util.Properties;

public class LocalStorage
{
    private File _file;
    private Properties _properties;

    public LocalStorage()
    {
        FileInputStream iStream;
        try
        {
            this._file = new File(System.getProperty("user.home"), "rfa.properties");
            this._properties = new Properties();
            if (this._file.exists())
            {
                this._properties.load(iStream = new FileInputStream(this._file));
                iStream.close();
            }
            System.out.println("Initialized local storage");
        }
        catch (Exception ignored)
        {
            System.err.println("Choked the local storage initialization");
        }
    }

    public Long getAsNumber(String key)
    {
        String s = this.getAsString(key);
        Long nb = null;

        if (s != null)
        {
            try
            {
                nb = Long.parseLong(s);
            }
            catch (NumberFormatException ignored) {}
        }
        return nb;
    }

    public Double getAsFloatingNumber(String key)
    {
        String s = this.getAsString(key);
        Double nb = null;

        if (s != null)
        {
            try
            {
                nb = Double.parseDouble(s);
            }
            catch (NumberFormatException ignored) {}
        }
        return nb;
    }

    public String getAsString(String key)
    {
        return this._properties.getProperty(key);
    }

    public LocalStorage store(String key, Object value)
    {
        if (this._file != null)
        {
            this._properties.setProperty(key, value.toString());
        }
        return this;
    }

    public LocalStorage delete(String key)
    {
        this._properties.remove(key);
        return this;
    }

    public boolean flush()
    {
        FileOutputStream oStream;

        if (this._file != null)
        {
            try
            {
                oStream = new FileOutputStream(this._file);
                this._properties.store(oStream, "RssFeedAggregator");
                oStream.close();
            }
            catch (IOException ignored)
            {
                return false;
            }
        }
        return true;
    }
}