package models;

import static com.google.common.base.Objects.toStringHelper;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Objects;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("user")
public class User
{
  public String firstName;
  public String lastName;
  public String email;
  public String password;
  @XStreamOmitField
  static Long counter = 0l;
  public Long id;
  public Map<Long, Activity> activities = new HashMap<>();
  
  public User(String firstName, String lastName, String email, String password)
  {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.id = counter++;
  }

  @Override
  public String toString()
  {
    return toStringHelper(this).addValue(firstName).addValue(lastName).addValue(password).addValue(email).addValue(activities).toString();
  }

  @Override
  public int hashCode()
  {
    return Objects.hashCode(this.lastName, this.firstName, this.email, this.password);
  }
}