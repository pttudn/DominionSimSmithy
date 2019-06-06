public class DominionSimSmithy{
    public static void main(String args[]){
        final int DECKMAXNUMBER = 100;
        final int HANDMAXNUMBER = 10;
        final int TRASHMAXNUMBER = 100;
        int turn = 1;
        System.out.println("ボードゲーム：ドミニオン用シミュレーター");
        System.out.println("鍛冶屋ステロのスピードを検証するためのプログラム");
        
        System.out.println("");

        String[] deck = new String[DECKMAXNUMBER];//デッキのカード一枚一枚を収納する配列
        String[] hand = new String[HANDMAXNUMBER];//ハンドのカードを一枚一枚収納する配列
        String[] trash = new String[TRASHMAXNUMBER];//トラッシュのカードを一枚一枚収納する配列

        for(int i=0;i<10;i++){
            if(i<=6){
                deck[i] = "C1";
            }else{
                deck[i] = "P1";
            }
        }
        for(int i=0;i<2;i++){
        printTurn(turn);
        printDeck(deck);//デッキの内容を表示
        drawDeck(hand,deck);//デッキからカードを５枚引く
        printHand(hand);//手札の内容を表示
        printDeck(deck);//デッキの内容を表示
        purchasePhase(deck,hand,trash);//カードを購入・デッキにカードを追加
        printTrash(trash);//トラッシュの内容を表示
        cleanUpPhase(deck,hand,trash);//クリーンナップ
        printHand(hand);//手札の内容を表示
        printDeck(deck);//デッキの内容を表示
        printTrash(trash);//トラッシュの内容を表示
        turn +=1;
        }

    }

    static void printTurn(int turn){
        System.out.println("---------現在 "+turn+" ターン目---------");
        System.out.println("");
    }

    public static void printDeck(String[] deck){
        System.out.println("---現在のデッキ---");
        for(int i=0;i<deck.length;i++){
            if(deck[i]==null){
                break;
            }else{
                System.out.print(deck[i]);
            }
        }
        System.out.println("");
        System.out.println("------------------");
        System.out.println("");
    }
    public static void printHand(String[] hand){
        System.out.println("---現在の手札---");
        for(int i=0;i<hand.length;i++){
            if(hand[i]==null){
                break;
            }else{
                System.out.print(hand[i]);
            }
        }
        System.out.println("");
        System.out.println("------------------");
        System.out.println("");
    }
    public static void printTrash(String[] trash){
        System.out.println("---現在のトラッシュ---");
        for(int i=0;i<trash.length;i++){
            if(trash[i]==null){
                break;
            }else{
                System.out.print(trash[i]);
            }
        }
        System.out.println("");
        System.out.println("------------------");
        System.out.println("");
    }


    public static void drawDeck(String[] hand,String[] deck) {
        for(int i=0;i<5;i++){
            hand[i] = deck[0];
            for(int j=1;j<deck.length;j++) deck[j-1]=deck[j];
        }
    }

    public static void purchasePhase(String[] deck,String[] hand,String[] trash){
        System.out.println("---購入フェイズでカードを購入---");
        int moneyvalue = moneyValue(hand);
        System.out.println("手札の金量： "+moneyvalue+" 金");
        purchaseCard(moneyvalue,trash);

    }

    public static int moneyValue(String[] hand){
        int value = 0;
        for(int i=0;i<hand.length;i++){
            if     (hand[i]=="C1") value += 1;
            else if(hand[i]=="C2") value += 2;
            else if(hand[i]=="C3") value += 3;
        }
        return value;
    }

    public static void purchaseCard(int moneyvalue, String[] trash){
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

    public static void addTrash(String name, String[] trash){
        for(int i=0;i<trash.length;i++){
            if(trash[i]==null){
                trash[i]=name;
                break;
            }
        }    
    }

    public static void cleanUpPhase(String[] deck,String[] hand,String[] trash){
        for(int i=0;i<hand.length;i++){
            if(hand[i]==null)break;
            else{
                addTrash(hand[i],trash);
                hand[i]="*";
            }
        }
    }

}