package org.example;

public class ReadNCharactersGivenRead4II {

    public static void main(String[] args) {

        Solution solution = new Solution("abcdefg");

        char[] buf1 = new char[3];
        int read1 = solution.read(buf1, 3);

        System.out.println(read1 + " -> " + new String(buf1));

        char[] buf2 = new char[2];
        int read2 = solution.read(buf2, 2);

        System.out.println(read2 + " -> " + new String(buf2));

        char[] buf3 = new char[4];
        int read3 = solution.read(buf3, 4);

        System.out.println(read3 + " -> " + new String(buf3));
    }
}

/*
 * Mock Reader4 class
 */
class Reader4 {

    private final String file;
    private int filePointer = 0;

    public Reader4(String file) {
        this.file = file;
    }

    /*
     * Mock implementation of read4()
     */
    public int read4(char[] buf4) {

        int count = 0;

        while (count < 4 &&
                filePointer < file.length()) {

            buf4[count] = file.charAt(filePointer);

            count++;
            filePointer++;
        }

        return count;
    }
}

/*
 * Actual Solution
 */
class Solution extends Reader4 {

    private final char[] internalBuffer = new char[4];

    private int bufferPtr = 0;
    private int bufferCount = 0;

    public Solution(String file) {
        super(file);
    }

    public int read(char[] buf, int n) {

        int totalRead = 0;

        while (totalRead < n) {

            // refill internal buffer if exhausted
            if (bufferPtr == bufferCount) {

                bufferCount = read4(internalBuffer);

                bufferPtr = 0;

                // EOF
                if (bufferCount == 0) {
                    break;
                }
            }

            // copy chars from internal buffer
            while (totalRead < n &&
                    bufferPtr < bufferCount) {

                buf[totalRead] =
                        internalBuffer[bufferPtr];

                totalRead++;
                bufferPtr++;
            }
        }

        return totalRead;
    }
}