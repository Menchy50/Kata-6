package software.ulpgc.kata6;

import java.util.stream.Stream;

public class RemoteStore {
    public Stream<Movie> movies() {
      return Stream.of(
        new Movie("Avatar", 2009, 162),
        new Movie("The Godfather", 1972, 120)
      );
    }
  }
