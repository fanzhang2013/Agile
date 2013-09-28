package models;

import static com.google.common.base.Objects.toStringHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.common.base.Objects;

public class Activity
{
  static Long counter = 0l;
  public Long id;
  public String type;
  public String location;
  public double distance;
  public Date date;
  public String duration;
  public List<Location> route = new ArrayList<>();

  public Activity(String type, String location, double distance, Date date,
      String duration)
  {
    this.id = counter++;
    this.type = type;
    this.location = location;
    this.distance = distance;
    this.date = date;
    this.duration = duration;
    
  }

  @Override
  public String toString()
  {
    return toStringHelper(this).addValue(id).addValue(type).addValue(location).addValue(distance)
        .addValue(date).addValue(duration).addValue(route).toString();
  }

  @Override
  public int hashCode()
  {
    return Objects.hashCode(this.id, this.type, this.location, this.distance, this.date, this.duration);
  }

}
