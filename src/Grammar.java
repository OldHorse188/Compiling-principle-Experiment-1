public class Grammar {

    private int[] type;
    private char[][] head;
    private char[][] body;


    public Grammar(int n) {
        this.type = new int[n];
        this.head = new char[n][];
        this.body = new char[n][];
    }

    // 设置第n个产生式的内容
    public void setN(int n, char[] head, char[] body) {
        this.head[n] = head;
        this.body[n] = body;
    }


    // 判断是否为终结符
    public boolean isTerminal(char chr) {
        return chr >= 97 && chr <= 122;
    }


    // 判断是否为非终结符
    public boolean isNonterminal(char chr) {
        return chr >= 65 && chr <= 90;
    }


    // 判断文法是否合法
    public boolean isLegal(int i) {
        if (head[i].length >= 1 && body[i].length >= 1) {
            for (char he:head[i]) {
                if (!(isTerminal(he) || isNonterminal(he)))
                    return false;
            }
            for (char bo:body[i]) {
                if (!(isTerminal(bo) || isNonterminal(bo)))
                    return false;
            }
        }
        return true;
    }


    // 0型文法判断（产生式的左部 是否 至少包含一个非终结符）
    public boolean isZero(int i)  {
        for (char he:head[i]) {
            if (isNonterminal(he))
                return true;
        }
        return false;
    }


    // 1型文法判断（产生式的左部符号个数 是否 不多于 右部）
    public boolean isOne(int i)  {
        return head[i].length <= body[i].length;
    }


    // 2型文法判断（产生式的左部 是否 为一个非终结符）
    public boolean isTwo(int i)  {
        return (head[i].length == 1) && (isNonterminal(head[i][0]));
    }


    // 3型文法判断（产生式的右部 是否 为一个终结符 或者 一个终结符和一个非终结符）
    public boolean isThree(int i)  {
        if(body[i].length == 1) {
            return isTerminal(body[i][0]);
        }
        else if(body[i].length == 2) {
            return (isTerminal(body[i][1]) && isNonterminal(body[i][0])) || (isTerminal(body[i][0]) && isNonterminal(body[i][1]));
        }
        else
            return false;
    }


    // 文法类型判断
    public void judge() {
        for (int i = 0; i < head.length; i++) {
            if (isLegal(i)) {
                if (isZero(i)) {
                    if (isOne(i)) {
                        if (isTwo(i)) {
                            if (isThree(i)) {
                                type[i] = 3;
                            }
                            else
                                type[i] = 2;
                        }
                        else
                            type[i] = 1;
                    }
                    else
                        type[i] = 0;
                }
                else
                    type[i] = -1;
            }
            else
                type[i] = -1;
        }
    }


    // 结果输出
    public void printResult()
    {
        int flag = 3;
        for (int i:type) {
            if (i < flag)
                flag = i;
        }

        switch(flag)
        {
            case -1:
                System.out.println("非法文法!");
                break;
            case 0:
                System.out.println("0型文法!");
                break;
            case 1:
                System.out.println("1型文法!");
                break;
            case 2:
                System.out.println("2型文法!");
                break;
            case 3:
                System.out.println("3型文法!");
                break;
        }
    }

}
