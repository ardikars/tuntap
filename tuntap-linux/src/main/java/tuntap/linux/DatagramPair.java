package tuntap.linux;

import tuntap.Pair;

class DatagramPair<L, R> implements Pair<L, R> {

    private final L left;
    private final R right;

    public DatagramPair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public L getLeft() {
        return left;
    }

    @Override
    public R getRight() {
        return right;
    }
}
