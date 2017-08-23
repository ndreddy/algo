

def can_two_movies_fill_flight(movie_lengths, flight_length):

    # movie lengths we've seen so far
    movie_lengths_seen = set()

    for first_movie_length in movie_lengths:

        matching_second_movie_length = flight_length - first_movie_length
        if matching_second_movie_length in movie_lengths_seen:
            print "Yes. You can see two full movies"
            return True

        movie_lengths_seen.add(first_movie_length)

    print "No you can't"
    # we never found a match, so return False
    return False

can_two_movies_fill_flight([10, 30, 70, 60, 90], 120)