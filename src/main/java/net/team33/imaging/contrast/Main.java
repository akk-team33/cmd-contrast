package net.team33.imaging.contrast;

public final class Main {

    private Main() {
    }

    public static void main(final String[] args){
        try {
            proceed(Args.build(args));
        } catch (Args.Problem problem) {
            problem.printStackTrace();
        }
    }

    private static void proceed(final Args args) {
        System.out.println(args);
    }
}
