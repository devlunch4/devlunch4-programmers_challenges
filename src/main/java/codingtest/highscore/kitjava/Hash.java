package codingtest.highscore.kitjava;

import java.util.*;

/**
 * hash; 해시
 * <br>
 * https://programmers.co.kr/learn/courses/30/parts/12077
 */
class Hash {
    //class Solution {

    public static void main(String[] args) {

        // Q1
        String[] participant = new String[]{"leo", "kiki", "eden"};
        String[] completion = new String[]{"eden", "kiki"};
        String firstAnswer = unFinisher(participant, completion);
        System.out.printf("hash Q1 :%s\n", firstAnswer);
        System.out.println("hash Q1 :" + firstAnswer);


        // Q2
        String[] phone_book = new String[]{"119", "97674223", "1195524421"};
        boolean phoneBook = phoneBookList(phone_book);
        System.out.printf("hash Q2 :%s\n", phoneBook);
        System.out.println("hash Q2 :" + phoneBook);

        // Q3
        String[][] clothes = new String[][]{{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}};
        int camouflage = camouflage(clothes);
        System.out.printf("hash Q3 :%s\n", camouflage);
        System.out.println("hash Q3 :" + camouflage);

        // Q4
        String[] genres = new String[]{"classic", "pop", "classic", "classic", "pop"};
        int[] plays = new int[]{500, 600, 150, 800, 2500};
        int[] bestAlbum = bestAlbum(genres, plays);
        System.out.printf("hash Q4 :%s\n", Arrays.toString(bestAlbum));
        System.out.println("hash Q4 :" + Arrays.toString(bestAlbum));


    /*
    https://codechacha.com/ko/java-printf-format/
    Syntax는 다음과 같습니다.

    System.out.printf(format, arguments)
    %s와 같은 지시자들은 아래와 같은 것들이 있습니다.

    %n : 줄바꿈
    %s : String 형식으로 출력
    %d : integer 형식으로 출력
    %f : float 형식으로 출력
    %t : date, time 형식으로 출력
    %o : 8진수 integer 형식으로 출력
    %x : 16진수 integer 형식으로 출력
    %b : boolean 형식으로 출력
    %e : 지수 형식으로 출력
    */

    }

    /**
     * Q4. Best Album;베스트앨범
     * <br>
     * https://programmers.co.kr/learn/courses/30/lessons/42579
     * <pre>
     *         문제 설명
     *         스트리밍 사이트에서 장르 별로 가장 많이 재생된 노래를 두 개씩 모아 베스트 앨범을 출시하려 합니다. 노래는 고유 번호로 구분하며, 노래를 수록하는 기준은 다음과 같습니다.
     *
     *         속한 노래가 많이 재생된 장르를 먼저 수록합니다.
     *                 장르 내에서 많이 재생된 노래를 먼저 수록합니다.
     *         장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.
     *                 노래의 장르를 나타내는 문자열 배열 genres와 노래별 재생 횟수를 나타내는 정수 배열 plays가 주어질 때, 베스트 앨범에 들어갈 노래의 고유 번호를 순서대로 return 하도록 solution 함수를 완성하세요.
     *
     *                 제한사항
     *         genres[i]는 고유번호가 i인 노래의 장르입니다.
     *                 plays[i]는 고유번호가 i인 노래가 재생된 횟수입니다.
     *         genres와 plays의 길이는 같으며, 이는 1 이상 10,000 이하입니다.
     *                 장르 종류는 100개 미만입니다.
     *         장르에 속한 곡이 하나라면, 하나의 곡만 선택합니다.
     *         모든 장르는 재생된 횟수가 다릅니다.
     *                 입출력 예
     *         genres	plays	return
     *         ["classic", "pop", "classic", "classic", "pop"]	[500, 600, 150, 800, 2500]	[4, 1, 3, 0]
     *         입출력 예 설명
     *         classic 장르는 1,450회 재생되었으며, classic 노래는 다음과 같습니다.
     *
     *                 고유 번호 3: 800회 재생
     *         고유 번호 0: 500회 재생
     *         고유 번호 2: 150회 재생
     *         pop 장르는 3,100회 재생되었으며, pop 노래는 다음과 같습니다.
     *
     *                 고유 번호 4: 2,500회 재생
     *         고유 번호 1: 600회 재생
     *         따라서 pop 장르의 [4, 1]번 노래를 먼저, classic 장르의 [3, 0]번 노래를 그다음에 수록합니다.
     *
     *         ※ 공지 - 2019년 2월 28일 테스트케이스가 추가되었습니다.</pre>
     *
     * @param genres input
     * @param plays  input
     * @return answer
     */
    private static int[] bestAlbum(String[] genres, int[] plays) {
        //public int[] solution (String[]genres,int[] plays){

        // 고유번호 = key값 & 장르, 플레이횟수 = value값
        HashMap<Integer, String> gMap = new HashMap<>();
        HashMap<Integer, Integer> pMap = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            gMap.put(i, genres[i]);
            pMap.put(i, plays[i]);
        }

        // HashSet으로 장르 분류
        HashSet<String> gSet = new HashSet<>();
        Collections.addAll(gSet, genres);

        // 장르별 총 플레이 횟수
        HashMap<Integer, String> coPlay = new HashMap<>();
        for (String x : gSet) {
            int count = 0;
            for (int i = 0; i < gMap.size(); i++) {
                if (gMap.get(i).equals(x)) {
                    count += pMap.get(i);
                }
            }
            coPlay.put(count, x);
        }

        // 플레이 횟수 별로 정렬(키 값을 기준으로 정렬, TreeMap)
        TreeMap<Integer, String> sort = new TreeMap<>(coPlay);
        String[] sortGenre = new String[gSet.size()];
        int index = 0;
        for (int o : sort.keySet()) {
            sortGenre[index] = sort.get(o);
            index++;
        }

        // 장르별 많이 플레이 된 노래의 고유번호 찾기
        ArrayList<Integer> fIndex = new ArrayList<>();
        for (int i = sortGenre.length - 1; i >= 0; i--) {
            int count = 0;
            for (int p1 : gMap.keySet()) {
                if (sortGenre[i].equals(gMap.get(p1))) {
                    count++;
                }
            }

            int[] temp = new int[count];
            int k = 0;
            for (int p2 : gMap.keySet()) {
                if (sortGenre[i].equals(gMap.get(p2))) {
                    temp[k] = pMap.get(p2);
                    k++;
                }
            }

            if (temp.length != 1) {
                Arrays.sort(temp);
                for (int j = temp.length - 1; j >= temp.length - 2; j--) {
                    for (int p : pMap.keySet()) {
                        if (temp[j] == pMap.get(p)) {
                            fIndex.add(p);
                            pMap.put(p, 0);
                            break;
                        }
                    }
                }
            } else {
                for (int p : pMap.keySet()) {
                    if (temp[0] == pMap.get(p)) {
                        fIndex.add(p);
                        pMap.put(p, 0);
                        break;
                    }
                }
            }
        }

        int[] answer = new int[fIndex.size()];

        for (int i = 0; i < fIndex.size(); i++) {
            answer[i] = fIndex.get(i);
        }

        return answer;

    }


    /**
     * Q3. camouflage; 위장
     * <br>
     * https://programmers.co.kr/learn/courses/30/lessons/42578
     * <pre>
     *         문제 설명
     *         스파이들은 매일 다른 옷을 조합하여 입어 자신을 위장합니다.
     *
     *         예를 들어 스파이가 가진 옷이 아래와 같고 오늘 스파이가 동그란 안경, 긴 코트, 파란색 티셔츠를 입었다면 다음날은 청바지를 추가로 입거나 동그란 안경 대신 검정 선글라스를 착용하거나 해야 합니다.
     *
     *         종류	이름
     *         얼굴	동그란 안경, 검정 선글라스
     *         상의	파란색 티셔츠
     *         하의	청바지
     *         겉옷	긴 코트
     *         스파이가 가진 의상들이 담긴 2차원 배열 clothes가 주어질 때 서로 다른 옷의 조합의 수를 return 하도록 solution 함수를 작성해주세요.
     *
     *         제한사항
     *         clothes의 각 행은 [의상의 이름, 의상의 종류]로 이루어져 있습니다.
     *         스파이가 가진 의상의 수는 1개 이상 30개 이하입니다.
     *         같은 이름을 가진 의상은 존재하지 않습니다.
     *         clothes의 모든 원소는 문자열로 이루어져 있습니다.
     *         모든 문자열의 길이는 1 이상 20 이하인 자연수이고 알파벳 소문자 또는 '_' 로만 이루어져 있습니다.
     *         스파이는 하루에 최소 한 개의 의상은 입습니다.
     *         입출력 예
     *         clothes	return
     *         [["yellowhat", "headgear"], ["bluesunglasses", "eyewear"], ["green_turban", "headgear"]]	5
     *         [["crowmask", "face"], ["bluesunglasses", "face"], ["smoky_makeup", "face"]]	3
     *         입출력 예 설명
     *         예제 #1
     *         headgear에 해당하는 의상이 yellow_hat, green_turban이고 eyewear에 해당하는 의상이 blue_sunglasses이므로 아래와 같이 5개의 조합이 가능합니다.
     *
     *         1. yellow_hat
     *         2. blue_sunglasses
     *         3. green_turban
     *         4. yellow_hat + blue_sunglasses
     *         5. green_turban + blue_sunglasses
     *         예제 #2
     *         face에 해당하는 의상이 crow_mask, blue_sunglasses, smoky_makeup이므로 아래와 같이 3개의 조합이 가능합니다.
     *
     *         1. crow_mask
     *         2. blue_sunglasses
     *         3. smoky_makeup</pre>
     *
     * @param clothes input
     * @return answer
     */
    private static int camouflage(String[][] clothes) {
        //public int solution(String[][] clothes) {
        System.out.println("Q3 solution: >input:" + Arrays.deepToString(clothes));
        HashMap<String, Integer> map = new HashMap<>();

        //종류를 검색하여 숫자를 확인한다.
        for (String[] clothe : clothes) {
            System.out.println(Arrays.toString(clothe));
            if (map.get(clothe[1]) == null) {
                System.out.println(">add category:" + clothe[1] + ":" + clothe[0]);
                map.put(clothe[1], 1);
            } else {
                System.out.println(">add category:" + clothe[1] + ":" + clothe[0]);
                map.put(clothe[1], map.get(clothe[1]) + 1);
            }
        }
        // 경우의 수 계산
        int answer = 1;
        String formula = "";
        int idx = 0;
        System.out.println(">map :" + map);
        for (String item : map.keySet()) {
            answer *= (map.get(item) + 1);
            if (idx == 0) {
                idx += 1;
                formula += "( " + map.get(item) + " + 1 )";
            } else {
                formula += " x ( " + map.get(item) + " + 1 )";
            }
        }

        answer -= 1;
        formula += " - 1 = " + answer;
        System.out.println(">formula: " + formula);
        return answer;

    }

    /**
     * Q2. phoneBookList; 전화번호 목록
     * <br>
     * https://programmers.co.kr/learn/courses/30/lessons/42576
     *  <pre>
     *         문제 설명
     *         전화번호부에 적힌 전화번호 중, 한 번호가 다른 번호의 접두어인 경우가 있는지 확인하려 합니다.
     *         전화번호가 다음과 같을 경우, 구조대 전화번호는 영석이의 전화번호의 접두사입니다.
     *
     *         구조대 : 119
     *         박준영 : 97 674 223
     *         지영석 : 11 9552 4421
     *         전화번호부에 적힌 전화번호를 담은 배열 phone_book 이 solution 함수의 매개변수로 주어질 때, 어떤 번호가 다른 번호의 접두어인 경우가 있으면 false를 그렇지 않으면 true를 return 하도록 solution 함수를 작성해주세요.
     *
     *         제한 사항
     *         phone_book의 길이는 1 이상 1,000,000 이하입니다.
     *         각 전화번호의 길이는 1 이상 20 이하입니다.
     *         같은 전화번호가 중복해서 들어있지 않습니다.
     *         입출력 예제
     *         phone_book	return
     *         ["119", "97674223", "1195524421"]	false
     *         ["123","456","789"]	true
     *         ["12","123","1235","567","88"]	false
     *         입출력 예 설명
     *         입출력 예 #1
     *         앞에서 설명한 예와 같습니다.
     *
     *         입출력 예 #2
     *         한 번호가 다른 번호의 접두사인 경우가 없으므로, 답은 true입니다.
     *
     *         입출력 예 #3
     *         첫 번째 전화번호, “12”가 두 번째 전화번호 “123”의 접두사입니다. 따라서 답은 false입니다.
     *
     *         알림
     *
     *         2021년 3월 4일, 테스트 케이스가 변경되었습니다. 이로 인해 이전에 통과하던 코드가 더 이상 통과하지 않을 수 있습니다.
     *         </pre>
     *
     * @param phone_book input
     * @return answer
     */
    private static boolean phoneBookList(String[] phone_book) {
        //public boolean solution (String[] phone_book){
        boolean answer = true;
        Arrays.sort(phone_book);
        for (int i = 0; i < phone_book.length - 1; i++) {
            if (phone_book[i + 1].startsWith(phone_book[i])) {
                answer = false;
                break;
            }
        }
        return answer;

    }

    /**
     * Q1. unFinisher; 완주하지 못한 선수
     * <br>
     * https://programmers.co.kr/learn/courses/30/lessons/42576
     * <pre>
     *         문제 설명
     *         수많은 마라톤 선수들이 마라톤에 참여하였습니다. 단 한 명의 선수를 제외하고는 모든 선수가 마라톤을 완주하였습니다.
     *
     *         마라톤에 참여한 선수들의 이름이 담긴 배열 participant와 완주한 선수들의 이름이 담긴 배열 completion이 주어질 때, 완주하지 못한 선수의 이름을 return 하도록 solution 함수를 작성해주세요.
     *
     *         제한사항
     *         마라톤 경기에 참여한 선수의 수는 1명 이상 100,000명 이하입니다.
     *         completion의 길이는 participant의 길이보다 1 작습니다.
     *         참가자의 이름은 1개 이상 20개 이하의 알파벳 소문자로 이루어져 있습니다.
     *         참가자 중에는 동명이인이 있을 수 있습니다.
     *         입출력 예
     *         participant	completion	return
     *         ["leo", "kiki", "eden"]	["eden", "kiki"]	"leo"
     *         ["marina", "josipa", "nikola", "vinko", "filipa"]	["josipa", "filipa", "marina", "nikola"]	"vinko"
     *         ["mislav", "stanko", "mislav", "ana"]	["stanko", "ana", "mislav"]	"mislav"
     *         입출력 예 설명
     *         예제 #1
     *         "leo"는 참여자 명단에는 있지만, 완주자 명단에는 없기 때문에 완주하지 못했습니다.
     *
     *         예제 #2
     *         "vinko"는 참여자 명단에는 있지만, 완주자 명단에는 없기 때문에 완주하지 못했습니다.
     *
     *         예제 #3
     *         "mislav"는 참여자 명단에는 두 명이 있지만, 완주자 명단에는 한 명밖에 없기 때문에 한명은 완주하지 못했습니다.
     *         </pre>
     *
     * @param participant input
     * @param completion  input
     * @return answer
     */
    private static String unFinisher(String[] participant, String[] completion) {
        //public String solution(String[] participant, String[] completion) {
        //맵생성
        Map<String, Integer> map = new HashMap<>();

        //참가자 넣기
        for (String arg : participant) map.put(arg, map.getOrDefault(arg, 0) + 1);
        //완주자 넣기
        for (String arg : completion) map.put(arg, map.get(arg) - 1);

        //키값에 해당값 리턴.
        for (String key : map.keySet()) {
            if (map.get(key) != 0) return key;
        }
        return null;

    }

}
