import java.util.ArrayList;
import java.util.Collections;

public class DominionSimSmithy{
    public static void main(String args[]){
        final int DECKMAXNUMBER = 100;
        final int HANDMAXNUMBER = 10;
        final int TRASHMAXNUMBER = 100;
        final int LOOPSIM = 100;
        int turn = 0;
        System.out.println("ボードゲーム：ドミニオン用シミュレーター");
        System.out.println("鍛冶屋ステロのスピードを検証するためのプログラム");
        System.out.println("");

        String[] deck = new String[DECKMAXNUMBER];//デッキのカード一枚一枚を収納する配列
        String[] hand = new String[HANDMAXNUMBER];//ハンドのカードを一枚一枚収納する配列
        String[] trash = new String[TRASHMAXNUMBER];//トラッシュのカードを一枚一枚収納する配列
        int[] result = new int[LOOPSIM];//シミュレーションの結果を保存する配列

        for(int i =0;i<LOOPSIM;i++){
            initialGenerateDeck(deck);//初期デッキを生成
            drawDeck(deck,hand,trash);//デッキからカードを５枚引く
            while(continueDecision(deck,hand,trash)){
                turn +=1;
                printTurn(turn);//ターン数を表示
                printHand(hand);//手札の内容を表示
                purchasePhase(deck,hand,trash);//購入フェイズのアクションを実行
                cleanUpPhase(deck,hand,trash);//クリーンナップフェイズのアクションを実行
                printHand(hand);//手札の内容を表示
                printDeck(deck);//デッキの内容を表示
                printTrash(trash);//トラッシュの内容を表示
            }
            printScore(turn,deck,hand,trash);//点数を表示
            result[i]=turn;
            turn = 0;
            ClearAll(deck,hand,trash);//データを初期化
        }

    }

    static void initialGenerateDeck(String[] deck){
        for(int i=0;i<10;i++){
            if(i<=6){
                deck[i] = "C1";
            }else{
                deck[i] = "P1";
            }
        }
        shuffleZone(deck);//デッキをシャッフル

    }

    static void printTurn(int turn){//現在のターンを表示
        System.out.println("\n\n---------現在 "+turn+" ターン目---------\n");
    }

    public static void printDeck(String[] deck){//現在のデッキの内容を表示
        System.out.println("---デッキ---");
        printAllCard(deck);
        System.out.println("\n------------------");
    }

    public static void printHand(String[] hand){//現在のハンドの内容を表
        System.out.println("---ハンド---");
        printAllCard(hand);
        System.out.println("\n------------------");
    }

    public static void printTrash(String[] trash){//現在のトラッシュの内容を表
        System.out.println("---トラッシュ---");
        printAllCard(trash);
        System.out.println("\n------------------");
    }

    public static void printAllCard(String[] zone){//zoneのカードを一枚ずつ表示
        for(int i=0;i<zone.length;i++){
            if(zone[i]==null){
                break;
            }else{
                System.out.print(zone[i]);
            }
        }
    }

    public static void printScore(int turn,String[] deck,String[] hand,String[] trash){//点数を表示
        int score = scoreValue(deck,hand,trash);
        System.out.println("ターン数： "+turn+"  点数： "+score +"\n");
    }

    public static void drawDeck(String[] deck,String[] hand,String[] trash) {//デッキからカードをドローする
        for(int i=0;i<5;i++){
            if(deck[0]==null) cleanUpDeck(deck,trash);
            hand[i] = deck[0];
            for(int j=1;j<deck.length;j++) deck[j-1]=deck[j];
        }
    }

    public static void cleanUpDeck(String[] deck,String[] trash){//トラッシュのカードをデッキに加えシャッフルする
        System.out.println("デッキシャッフル");
        for(int i=0;i<trash.length;i++){
            if(trash[i]==null)break;
            deck[i]=trash[i];
            trash[i]=null;
        }
        shuffleZone(deck);
    }

    public static void purchasePhase(String[] deck,String[] hand,String[] trash){//購入フェイズのアクション
        System.out.println("---購入フェイズでカードを購入---");
        int moneyvalue = moneyValue(hand);
        System.out.println("手札の金量： "+moneyvalue+" 金");
        purchaseCard(moneyvalue,trash);

    }

    public static int moneyValue(String[] hand){//手札の金量を計算する
        int value = 0;
        for(int i=0;i<hand.length;i++){
            if     (hand[i]=="C1") value += 1;
            else if(hand[i]=="C2") value += 2;
            else if(hand[i]=="C3") value += 3;
        }
        return value;
    }

    public static int scoreValue(String[] deck, String[] hand, String[] trash){//点数を計算
        int value = 0;
        for(int i=0;i<deck.length;i++){
            if     (deck[i]=="P1") value += 1;
            else if(deck[i]=="P2") value += 3;
            else if(deck[i]=="P3") value += 6;
        }
        for(int i=0;i<hand.length;i++){
            if     (hand[i]=="P1") value += 1;
            else if(hand[i]=="P2") value += 3;
            else if(hand[i]=="P3") value += 6;
        }
        for(int i=0;i<trash.length;i++){
            if     (trash[i]=="P1") value += 1;
            else if(trash[i]=="P2") value += 3;
            else if(trash[i]=="P3") value += 6;
        }
        return value;
    }

    public static void purchaseCard(int moneyvalue, String[] trash){//金量に合わせてカードを購入する
        switch(moneyvalue){
            case 0: System.out.println("購入しませんでした\n"); break;  
            case 1: System.out.println("購入しませんでした\n"); break;  
            case 2: System.out.println("購入しませんでした\n"); break; 
            case 3: addTrash("C2",trash);
                    System.out.println("C2 を購入しました\n"); break; 
            case 4: addTrash("C2",trash);
                    System.out.println("C2 を購入しました\n"); break;
            case 5: addTrash("C2",trash);
                    System.out.println("C2 を購入しました\n"); break; 
            case 6: addTrash("C3",trash);
                    System.out.println("C3 を購入しました\n"); break; 
            case 7: addTrash("C3",trash);
                    System.out.println("C3 を購入しました\n"); break; 
            default: addTrash("P3",trash);
                    System.out.println("P3 を購入しました\n"); break; 
        }
    }

    public static void addTrash(String name, String[] trash){//捨て札の末尾にカードを追加する
        for(int i=0;i<trash.length;i++){
            if(trash[i]==null){
                trash[i]=name;
                break;
            }
        }    
    }

    public static void cleanUpPhase(String[] deck,String[] hand,String[] trash){//クリーンアップフェイズのアクション
        System.out.println("---クリーンナップフェイズ---");
        for(int i=0;i<hand.length;i++){
            if(hand[i]==null)break;
            else{
                addTrash(hand[i],trash);
                hand[i]="*";
            }
        }
        drawDeck(deck,hand,trash);
        System.out.println("");
    }

    public static void shuffleZone(String[] zone){//配列をシャッフルする
        ArrayList<String> list = new ArrayList<String>();
        for(int i=0;i<zone.length;i++){
            if(zone[i]==null){
                break;
            }
            list.add(zone[i]);
        }
        Collections.shuffle(list);
        int i=0;
        for(String n:list) {
            zone[i]=n;
            i++;
        }
    }

    public static boolean continueDecision(String[] deck,String[] hand,String[] trash){//ゲームの継続判定
        boolean b = (scoreValue(deck,hand,trash) < 27) ;
        return b;
    }

    public static void ClearAll(String[] deck,String[] hand,String[] trash){
        for(int i=0;i<deck.length;i++){
            if(deck[i]==null)break;
            else{
                deck[i]=null;
            }
        }
        for(int i=0;i<hand.length;i++){
            if(hand[i]==null)break;
            else{
                hand[i]=null;
            }
        }
        for(int i=0;i<trash.length;i++){
            if(trash[i]==null)break;
            else{
                trash[i]=null;
            }
        }
    }

}