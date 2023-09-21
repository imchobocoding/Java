//-----------------------------------------------------------------------------

//
// CH2: Main.java
//
//  Created on: 2023. 9. 20.
//      Author: 김평강
//
//  + CurrentUser, MultiManager, Person 클래스 추가
//  + MainMenu 수정
//

import java.util.*;

//===============================================================================
// main()
//===============================================================================
public class Main {
    public static void main(String[] args) {
        //--------------------------------
        // AutoCheck(chk, trace)
        // chk: 1(자동 오류 체크), 0(키보드에서 직접 입력하여 프로그램 실행)
        // trace: true(오류발생한 곳 출력), false(단순히 O, X만 표시)
        //--------------------------------
        // int chk = 1; if (chk != 0) new AutoCheck(chk, true).run(); else

        // TODO: System.in을 인자로 하는 Scanner 객체를 생성한 후
        //       해당 객체를 아래 run() 함수의 인자로 넘겨 주어라. (null 대신에 넘겨 줄 것)
        //       위 기능을 반드시 하나의 문장으로 완성해야 한다. 변수 선언하지 말고 바로 넘겨 줌
        //       즉, run( Scanner 객체를 생성 ); 형태가 되어야 한다.
        run(new Scanner(System.in));
    }

    public static void run(Scanner scan) {
        // UI 클래스의 setScanner() 함수를 호출함; setScanner()가 static 함수라 이렇게 호출 가능함
        UI.setScanner(scan); // UI 클래스 내의 static 함수 호출
        MainMenu.run(); // MainMenu 클래스 내의 static 함수 호출방법: 클래스이름.함수이름();
        // TODO: scan이 더 이상 필요없으므로 닫아라.
        scan.close();
    }
}

//===============================================================================
// Main Menu
//===============================================================================
class MainMenu {
    public static void run() {
        final int MENU_COUNT = 4;  // 메뉴 항목 개수 선언
        String menuStr =
                "******* Main Menu ********\n" +
                        "* 0.exit 1.PersonManager *\n" +
                        "* 2.ch2 3.ch3            *\n" +
                        "**************************\n";

        int menuItem;

        // while 문을 이용한 메뉴 반복
        while (true) {
            menuItem = UI.selectMenu("\n" + menuStr, MENU_COUNT);
            switch (menuItem) {
                case 0:
                    System.out.println("\nGood bye!!");
                    return;
                case 1:
                    Ch2.run();
                    break;
                case 2:
                    Ch3.run();
                    break;
                case 3:
                    break;
            }
        }
        // TODO: 입력 받은 memnuItem 값을 프로그램 실행결과처럼 출력하라. 예) menu item: 1
    }
} // class MainMenu

class Person
{
    private String  name;    // 이름
    private int     id;      // Identifier
    private double  weight;  // 체중
    private boolean married; // 결혼여부
    private String  address; // 주소

    // 생성자 함수들
    public Person(String name, int id, double weight, boolean married, String address) {
    }

    public void println() {
        print(); System.out.println();
    }

    public void println(String msg) {
        System.out.print(msg); print(); System.out.println();
    }
    // assign() 함수

    // Getter: getXXX() 관련 함수들

    // Setter: overloading: set() 함수 중복

    // Candidates for virtual functions and overriding
    // print(), clone(), whatAreYouDoing(), equals(), input() 함수
    public void print() {
        System.out.print(name+" "+id+" "+weight+" "+married+" :"+address+":");
    }
    public void input(Scanner s) {
        // TODO: 스캐너 s로부터 name, id, weight, married 멤버를 입력 받을 것

        // 아래는 주소 필드를 입력 받는 부분이며 수정없이 그대로 사용하면 된다.
        // :로 시작하고 :로 끝나는 부분의 서브 문자열을 읽어 냄
        while ((address = s.findInLine(":.*:")) == null)
            s.nextLine();
        address = address.substring(1, address.length()-1);
        // 양쪽의 : :를 제거한 서브 문자열을 넘겨 받음
    }
}

//===============================================================================
// User Interface
//===============================================================================
class UI {
    public static boolean echo_input = false; // 자동오류체크 시 true로 설정됨
    public static Scanner scan;

    public static void setScanner(Scanner s) {
        scan = s;
    }

    // 사용자에게 메뉴("\n"+menuStr+"menu item? ")를 보여주고
    // 사용자가 선택한 메뉴항목의 인덱스(0 ~ (menuCount-1))를 리턴함
    // menuCount: 메뉴항목의 개수임
    public static int selectMenu(String menuStr, int menuCount) {

        int value;

        while (true) {
            System.out.print(menuStr);
            value = getIndex("menu item? ", menuCount);
            if (value >= 0 && value < menuCount) {
                break;
            } else {
                System.out.println("Invalid selection. Try again.");
            }
        }
        return value;
    }

    // 입력을 받기 위해 static Scanner scan 멤버를 활용하라. 즉, scan.함수() 형식으로 호출
    public static int getInt(String msg) {

        // TODO: msg를 화면에 출력한 후 정수 값을 입력 받아 지역 변수 value에 저장함 (변수 선언할 것)
        //       입력 시 이 클래스의 scan 멤버 변수를 활용하라.
        //       (이 변수는 setScanner(Scanner s)에 의해 이미 초기화 되었음)
        int value;

        while (true) { // 무한 루프
            try {
                System.out.print(msg);  // 메시지 출력
                value = scan.nextInt();  // 정수 입력
                break;  // 정상적으로 입력되면 루프 종료
            } catch (InputMismatchException e) {  // 예외 발생시
//                System.out.println(e); // 예외 종류 출력
//                System.out.println("-----");
//                e.printStackTrace();   // 예외에 대한 상세한 정보 출력

                System.out.println("Input an INTEGER. Try again!!");  // 에러 메시지 출력
                scan.nextLine();  // 버퍼 클리어
            }
        }


        if (echo_input) System.out.println(value); // 자동오류체크 시 입력 값을 출력함
        // 위 문장은 자동오류체크 시에 사용되는 문장임; 일반적으로 키보드로부터 입력받을 경우
        // 화면에 자동 echo되지만, 자동오류체크 시에는 입력파일에서 입력받은 값이 자동 echo 되지
        // 않으므로 명시적으로 출력 버퍼에 출력(echo) 해 주어야 한다.

        // (지시가 있을 때 구현할 것) 입력 버퍼에 남아 있는 '\n'를 제거하지 않으면 다음번 getLine()에서
        // '\n'만 빈 줄이 입력될 수 있으므로 입력 버퍼에 남아 있는 '\n'를 사전에 제거함

        return value; // TODO: 입력 받은 정수 value를 리턴할 것
    }

    // [0, (size-1)] 사이의 인덱스 값을 리턴함
    // 존재하지 않는 메뉴항목을 선택한 경우 에러 출력
    public static int getIndex(String msg, int size) {
        // TODO: 위 적절한 함수를 호출해 0 또는 양수를 입력 받은 후 적절하지 않은 인덱스(index)일 경우
        //       에러("index: OUT of selection range(0 ~ size-1) Try again!!")를
        //       출력하고 다시 입력 받는다.
        int value;
        while (true) {
            value = getPosInt(msg);  // 양수만 받는 getPosInt()를 호출
            if (value >= size) {  // 유효한 인덱스 범위인지 확인
                System.out.println(value + ": OUT of selection range(0 ~ " + (size - 1) + "). Try again!!");
            } else {
                break;  // 입력이 유효한 범위에 있을 경우에는 while 루프를 빠져나감
            }
        }
        return value; // TODO: 입력 받은 값 리턴
    }

    // 0 또는 양의 정수 값을 입력 받아 리턴함
    public static int getPosInt(String msg) {
        // TODO: 위 적절한 함수를 호출해 정수값을 입력 받은 후 입력된 값이 음수일 경우
        //       에러("Input a positive INTEGER. Try again!!") 출력하고 다시 입력 받는다.
        //       원하는 값이 입력될 때까지 위 과정을 계속 반복하여야 한다.
        int value;
        while (true) {
            value = getInt(msg);  // 정수를 받는 getInt()를 호출
            if (value >= 0) {  // 양수인지 확인
                break;
            } else {  // 음수일 경우 경고 메시지 출력
                System.out.println("Input a positive INTEGER. Try again!!");
            }
        }
        return value; // TODO: 입력된 0 또는 양수 리턴
    }

    // 위 getInt()를 참고하여 msg를 화면에 출력한 후 문자열 단어 하나를 입력 받아 리턴함
    public static String getNext(String msg) {
        System.out.print(msg);
        String token = scan.next();
        // TODO: msg를 화면에 출력한 후 하나의 토큰(단어)을 입력 받아 변수 token에 저장함
        if (echo_input) System.out.println(token); // 자동오류체크 시 입력 값을 출력함
        return token;  // TODO: 입력 받은 한 단어를 리턴할 것
    }

    // msg를 화면에 출력한 후 하나의 행 전체를 입력 받아 리턴함
    public static String getLine(String msg) {
        System.out.print(msg);
        String line = scan.nextLine();  // 한 줄 전체를 읽음
        // TODO: msg를 화면에 출력한 후 한 행 전체를 입력 받아 변수 line에 저장함
        if (echo_input) System.out.println(line); // 자동체크 시 출력됨
        return line;  // TODO: 입력 받은 한 행 전체를 리턴할 것
    }
}

//===============================================================================
// CurrentUser class: ch4_1
//===============================================================================
class CurrentUser
{
    Person user;

    CurrentUser(Person user) {
        this.user = user;
    }

    public void run() {
        String menuStr =
                "++++++++++++++++++++++ Current User Menu ++++++++++++++++++++++++\n" +
                        "+ 0.logout 1.display 2.getter 3.setter 4.copy 5.whatAreYouDoing +\n" +
                        "+ 6.equals 7.update                                             +\n" +
                        "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n";
        final int MENU_COUNT = 8; // 상수 정의
        while (true) {
            int menuItem = UI.selectMenu(menuStr, MENU_COUNT);
            switch(menuItem) {
                case 1: display();         break;
                case 2: getter();          break;
                case 3: setter();          break;
                case 4: copy();            break;
                case 5: whatAreYouDoing(); break;
                case 6: equals();          break;
                case 7: update();          break;
                case 0:                    return;
            }
        }
    }
    void display() {
        user.println();
    } // Menu item 1

    void getter() { // Menu item 2
    }
    void setter() { // Menu item 3
    }
    void copy() { // Menu item 4
    }
    void whatAreYouDoing() {  // Menu item 5
    }
    void equals() { // Menu item 6
    }
    void update() { // Menu item 7
    }
} // CurrentUser class: ch4_1

//===============================================================================
// class MultiManager: ch4_1
//===============================================================================
class MultiManager
{
    private Person person = new Person("p0", 0, 70.0, false, "Gwangju Nam-gu 21");

    void run() {
        new CurrentUser(person).run();
    }
} // class MultiManager: ch4_1


class Ch3 {
    public static void run() {
        final int MENU_COUNT = 4; // 메뉴 항복 개수
        String menuStr =
                "************* Ch3 Menu **************\n" +
                        "* 0.Exit 1.array 2.exception 3.game *\n" +
                        "*************************************\n";

        // TODO: Ch2::run() 함수를 참고하여 while문과 switch문을 작성하라.
        //       switch에서는 아래의 상응하는 함수를 호출하고, MENU_COUNT도 적절히 수정하라.
        int menuItem;

        while (true) {
            menuItem = UI.selectMenu("\n" + menuStr, MENU_COUNT);
            switch (menuItem) {
                case 0:
                    return;
                case 1:
                    array();
                    break;
                case 2:
                    exception();
                    break;
                case 3:
                    game();
                    break;
            }
        }
    }

    public static void array() {
        double arr1[][] = {
                {0},
                {1, 2},
                {3, 4, 5}
        };
        printArray(arr1);
        double arr2[][] = {
                {0, 1, 2, 3},
                {4, 5, 6},
                {7, 8},
                {9}};
        printArray(arr2);

        var arr3 = inputArray();
        printArray(arr3);
        arr3 = inputArray();
        printArray(arr3);
    }

    // 이 함수는 printArray() 함수 아래에 위치해야 합니다.
    public static double[][] inputArray() {
        int numRows = UI.getPosInt("array rows?"); // 행의 개수 입력
        double[][] arr = new double[numRows][]; // 배열 선언 및 메모리 할당

        for (int i = 0; i < numRows; i++) {
            arr[i] = new double[i + 1]; // 각 행에 메모리 할당

            System.out.printf("input" + (i + 1) + "doubles for row" + i + ": ");
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = UI.scan.nextDouble(); // 원소 입력
            }
        }
        return arr;
    }

    public static void printArray(double arr[][]) {
        // 출력: 배열의 전체 길이
        System.out.println("arr length: " + arr.length);

        // 각 행을 순회하며 출력
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[" + i + "]");

            for (int j = 0; j < arr[i].length; j++) {
                System.out.printf(arr[i][j] + " ");
            }

            // 각 행을 출력한 후에는 줄바꿈
            System.out.println();
        }

        // 전체 배열을 출력한 후에는 빈 줄을 추가
        System.out.println();
    }

    static Random random = null; // 난수 발생기

    public static void exception() {
        var random = new Random(UI.getInt("seed for random number? "));// 난수 발생기
        while (true) {
            try {
                String str = UI.getNext("array[] size? ");
                int i = Integer.parseInt(str);   // 문자열 숫자를 정수로 변환: "123" -> 123
                int arr[] = new int[i];

                for (i = 0; i < arr.length; ++i) // arr[] 전체를 난수 값으로 초기화
                    arr[i] = random.nextInt(3);  // [0,1,2] 범위의 난수 발생
                System.out.print("array[]: { ");
                for (var v : arr)                // 배열 전체 출력
                    System.out.print(v + " ");     // 각각의 v=arr[i] 원소 값을 출력함
                System.out.println("}");

                while (true) {
                    try {
                        i = UI.getPosInt("array[] index? ");
                        System.out.println("array[" + i + "] = " + arr[i]);
                        break;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(e);
                    }
                }

                while (true) {
                    try {
                        int numerator = UI.getIndex("numerator   index? ", arr.length); // 분자 index
                        while (true) {
                            try {
                                int denominator = UI.getIndex("denominator index? ", arr.length); // 분모 index
                                System.out.println(arr[numerator] + " / " + arr[denominator] + " = "
                                        + (arr[numerator] / arr[denominator]));
                                break;
                            } catch(ArithmeticException e) {
                                System.out.println(e);
                            }
                        }
                        break;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(e);
                    }
                }

                System.out.println("makeArray(): first");
                while (true) {
                    try {
                        arr = makeArray();
                        break;
                    } catch (OutOfMemoryError e) {
                        System.out.println(e);
                    } catch (NullPointerException e) {
                        System.out.println("NullPointerException");
                    }
                }

                System.out.println("makeArray(): second");
                while (true) {
                    try {
                        arr = makeArray();
                        System.out.println("array length = " + arr.length);
                        break;
                    } catch (OutOfMemoryError e) {
                        System.out.println(e);
                    } catch (NullPointerException e) {
                        System.out.println("NullPointerException");
                    }
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println(e);
            } catch (NegativeArraySizeException e) {
                System.out.println(e);
            }
        }
    }

    // tag 0: OutOfMemoryError, 1: return null pointer, 2: return normal array
    public static int[] makeArray() {
        int tag = UI.getPosInt("makeArray tag[0,1,2]? ");
        return (tag == 0) ? new int[0x7fffffff] : (tag == 1) ? null : new int[10];
    }

    public static void game() {
        final int USER = 0;     // 상수 정의
        final int COMPUTER = 1;
        String MJBarray[] = { "m", "j", "b" }; // 묵(m) 찌(j) 빠(b) 문자열을 가진 배열
        System.out.println("Start the MUK-JJI-BBA game.");
        // 난수 발생기
        random = new Random(UI.getInt("seed for random number? "));
        // 누가 우선권을 가졌는지 저장하고 있음, USER:사용자 우선권, COMPUTER:computer 우선권
        int priority = USER;
        String priStr[] = { "USER", "COMPUTER"}; // 우선권을 화면에 출력할 때 사용할 문자열

        while(true) {
            System.out.println("\n" + priStr[priority] + " has the higher priority.");
            String user = UI.getNext("m(muk), j(jji), b(bba) or stop? ");

            if (user.equals("stop")) {
                break;
            } else if (!user.equals("m") && !user.equals("j") && !user.equals("b")) {
                System.out.println("Select one among m, j, b.");
                continue;
            }

            // [0,1,2] 난수를 이용하여 COMPUTER가 묵찌빠 중 하나를 선택함
            String computer = MJBarray[random.nextInt(MJBarray.length)];
            System.out.print("User = " + user + ", Computer = " + computer + ", ");

            if (user.equals(computer)) {
                System.out.println(priStr[priority] + " WINs.");
            } else {
                System.out.print("SAME.");
                if ((user.equals("m") && computer.equals("j")) ||
                        (user.equals("j") && computer.equals("b")) ||
                        (user.equals("b") && computer.equals("m"))) {
                    priority = USER;
                } else {
                    priority = COMPUTER;
                }
                System.out.println();
            }
        }
    }
}

//===============================================================================
// class Ch2
//===============================================================================
class Ch2 {
    public static void run() {
        final int MENU_COUNT = 6; // 메뉴 항목 개수 선언
        String menuStr =
                "************* Ch2 Menu ***********\n" +
                        "* 0.exit 1.output 2.readToken    *\n" +
                        "* 3.readLine 4.operator 5.switch *\n" +
                        "**********************************\n";

        int menuItem;

        // while 문을 이용한 메뉴 반복
        while (true) {
            menuItem = UI.selectMenu("\n" + menuStr, MENU_COUNT);
            switch (menuItem) {
                case 0:
                    return;
                case 1:
                    output();
                    break;
                case 2:
                    readToken();
                    break;
                case 3:
                    readLine();
                    break;
                case 4:
                    operator();
                    break;
                case 5:
                    switchCase();
                    break;
                default:
                    System.out.println(menuItem + ": OUT of selection range(0 ~ 5) Try again!!");
            }
        }
    }

    public static void output() {
        String toolName = "JDK";
        double version = 1.8;
        String released = "is released.";

        // TODO: 하나의 출력문으로 위 세 변수를 이용하여 "JDK1.8is released."가 출력되게 하라.
        System.out.println(toolName + version + released);
        // TODO: 하나의 출력문으로 위 세 변수를 이용하여 "JDK 1.8 is released."가 출력되게 하라.
        System.out.println(toolName + " " + version + " " + released);
        int i1 = 1, i2 = 2, i3 = 3; // 변수 선언과 함께 초기화

        // TODO: 하나의 출력문으로 위 세 변수를 이용하여 "6"가 출력되게 하라.
        System.out.println(i1 + i2 + i3);
        // TODO: 하나의 출력문으로 위 세 변수를 이용하여 "123"가 출력되게 하라.
        System.out.println("" + i1 + i2 + i3);
        // TODO: 하나의 출력문으로 위 세 변수를 이용하여 "6 123"가 출력되게 하라.
        System.out.println(i1 + i2 + i3 + " " + i1 + i2 + i3);

        boolean b = true;
        double d = 1.2;

        // TODO: 하나의 출력문으로 위 두 변수를 이용하여 "true false 1.2"가 출력되게 하라.
        System.out.println(b + " " + !b + " " + d);
        //       변수만 사용하고 "true", "false"를 직접 출력하지는 마라.
    }

    public static void readToken() {
        String name;    // 이름
        int id;      // Identifier
        double weight;  // 체중
        boolean married; // 결혼여부
        String address; // 주소

        // TODO: 아래 실행결과를 참고하여 "person ... ):" 문자열을 출력하라.
        System.out.println("person information(name id weight married :address:):");
        // TODO: UI.scan을 이용하여 name, id, weight, married 값을 입력 받아라.
        name = UI.scan.next();
        id = UI.scan.nextInt();
        weight = UI.scan.nextDouble();
        married = UI.scan.nextBoolean();

        // 주소의 패턴 ":address:"을 읽어 들임: 이미 완성된 코드이므로 아래 address를 바로 활용하면 됨
        while ((address = UI.scan.findInLine(":.*:")) == null)
            UI.scan.nextLine();  // 현재 행에 주소가 없다면 그 행을 스킵함
        address = address.substring(1, address.length() - 1); // 주소 양 끝의 ':' 문자 삭제

        // TODO: "이름 id 몸무게 혼인여부 :주소:"를 출력함
        System.out.println(name + " " + id + " " + weight + " " + married + " :" + address + ":");
    }

    public static void readLine() {
        String name = UI.getNext("name? "); // "name? "을 출력한 후 이름을 입력 받음
        // TODO: 실행결과("name: p")처럼 출력
        System.out.println("name: " + name);
        int id = UI.getInt("id? ");         // "id? "을 출력한 후 id을 입력 받음
        // TODO: 실행결과("id: 1")처럼 출력
        System.out.println("id: " + id);

        // 입력 버퍼에 남아 있는 '\n'를 제거
        UI.scan.nextLine();

        String address = UI.getLine("address? ");  // "address? " 출력 후 한 줄 전체를 입력 받음
        System.out.println("address :" + address + ":");  // "address :seoul gangnam:"처럼 출력
    }

    public static void printBinStr(int v) {
        String s = Integer.toBinaryString(v);
        for (int i = 0; i < (32 - s.length()); ++i)
            System.out.print('0');
        System.out.println(s);
    }

    public static void operator() {
        int b = 0b11111111_00000000_11111111_11111111;
        printBinStr(b);

        // TODO: 변수 b를 왼쪽으로 4비트 이동시킨 값을 출력하라.
        printBinStr(b << 4);

        System.out.println();
        b = 0b11111111_00000000_00000000_11111111;
        printBinStr(b);

        // TODO: 변수 b를 4비트 산술적 오른쪽 시프트를 한 값을 출력하라.
        printBinStr(b >> 4);

        // TODO: 변수 b를 4비트 논리적 오른쪽 시프트를 한 값을 출력하라.
        printBinStr(b >>> 4);
    }

    public static void switchCase() {
        String menuStr =
                "********* Switch Menu *********\n" +
                        "* 0.exit 1.output 2.readToken *\n" +
                        "* 3.readLine 4.operator       *\n" +
                        "*******************************\n";

        while (true) {
            String menu = UI.getNext("\n" + menuStr + "menu item string? ");
            // menu는 메뉴항목 번호가 아닌 메뉴항목 단어를 직접 입력 받은 것임
            // TODO: Ch2.run()을 참조하여 switch 문장을 이용하여 상응하는 함수를 호출하라.
            //      단, 입력된 메뉴항목이 정수가 아니라 문자열(menu)임을 명심하라.
            //      즉, case 문장이 정수가 아니라 문자열과 비교 되어야 한다.
            switch (menu) {
                case "exit":
                    return;
                case "output":
                    output();
                    break;
                case "readToken":
                    readToken();
                    break;
                case "readLine":
                    readLine();
                    break;
                case "operator":
                    operator();
                    break;
            }
        }
    }
}

//-----------------------------------------------------------------------------
// 소스 파일 끝
//-----------------------------------------------------------------------------
