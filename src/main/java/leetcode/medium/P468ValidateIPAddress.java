package leetcode.medium;

public class P468ValidateIPAddress {
  public String validIPAddress(String IP) {
    if (isIPv4(IP)) {
      return "IPv4";
    } else if (isIPv6(IP)) {
      return "IPv6";
    } else {
      return "Neither";
    }
  }

  private boolean isIPv4(String IP) {
    if (IP.chars().filter(e -> e == '.').count() != 3) {
      return false;
    }
    String[] splits = IP.split("\\.");
    boolean matches = true;
    if (splits.length == 4) {
      for (String split : splits) {
        if (!split.matches("^(?!0)[0-9]{1,3}") && !split.matches("0")) {
          matches = false;
          break;
        }
        int val = Integer.parseInt(split);
        if (val > 255) {
          matches = false;
          break;
        }
      }
    } else {
      matches = false;
    }
    return matches;
  }

  private boolean isIPv6(String IP) {
    String[] splits = IP.split(":");
    boolean matches = true;
    if (IP.chars().filter(e -> e == ':').count() != 7) {
      return false;
    }
    if (splits.length == 8) {
      for (String split : splits) {
        if (!split.matches("[A-Fa-f0-9]{1,4}")) {
          matches = false;
          break;
        }
      }
    } else {
      matches = false;
    }
    return matches;
  }

  public static void main(String[] args) {
    System.out.println(new P468ValidateIPAddress().validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334"));
  }
}
