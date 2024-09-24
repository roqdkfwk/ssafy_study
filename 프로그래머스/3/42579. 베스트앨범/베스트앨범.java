import java.util.*;

class Solution {
    class Song {
        int id, plays;
        Song(int id, int plays) {
            this.id = id;
            this.plays = plays;
        }
    }

    public int[] solution(String[] genres, int[] plays) {
        Map<String, PriorityQueue<Song>> genreSongs = new HashMap<>();
        Map<String, Integer> genreTotalPlays = new HashMap<>();

        // 데이터 처리
        for (int i = 0; i < genres.length; i++) {
            genreSongs.computeIfAbsent(genres[i], k -> new PriorityQueue<>((a, b) -> b.plays == a.plays ? a.id - b.id : b.plays - a.plays))
                      .offer(new Song(i, plays[i]));
            genreTotalPlays.put(genres[i], genreTotalPlays.getOrDefault(genres[i], 0) + plays[i]);
        }

        // 장르 정렬
        List<String> sortedGenres = new ArrayList<>(genreTotalPlays.keySet());
        sortedGenres.sort((a, b) -> genreTotalPlays.get(b) - genreTotalPlays.get(a));

        // 결과 생성
        List<Integer> answer = new ArrayList<>();
        for (String genre : sortedGenres) {
            PriorityQueue<Song> songs = genreSongs.get(genre);
            int count = 0;
            while (!songs.isEmpty() && count < 2) {
                answer.add(songs.poll().id);
                count++;
            }
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}