package interview.adobe;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MirrorTreeTest {

  private MirrorTree mirrorTree;

  @Before
  public void setUp() {
    mirrorTree = new MirrorTree();
  }

  @Test
  public void givenNull_whenMirror_thenNull() {
    assertNull("Checking null", mirrorTree.mirror(null));
  }

  @Test
  public void givenValidTree_whenMirror_thenCorrect() {
    MirrorTree.BinaryTreeNode<String> root = createTree();
    MirrorTree.BinaryTreeNode<String> mirror = mirrorTree.mirror(root);
    validateMirror(mirror);
  }

  private void validateMirror(MirrorTree.BinaryTreeNode<String> mirror) {
    assertEquals(mirror.getData(), "A");
    assertEquals(mirror.getLeftChild().getData(), "E");
    assertEquals(mirror.getRightChild().getData(), "B");
    assertEquals(mirror.getLeftChild().getLeftChild().getData(), "F");
    assertEquals(mirror.getRightChild().getLeftChild().getData(), "D");
    assertEquals(mirror.getRightChild().getRightChild().getData(), "C");
  }

  private MirrorTree.BinaryTreeNode<String> createTree() {
    MirrorTree.BinaryTreeNode<String> root = new MirrorTree.BinaryTreeNode<>("A");
    MirrorTree.BinaryTreeNode<String> childB = new MirrorTree.BinaryTreeNode<>("B");
    MirrorTree.BinaryTreeNode<String> childE = new MirrorTree.BinaryTreeNode<>("E");
    MirrorTree.BinaryTreeNode<String> childC = new MirrorTree.BinaryTreeNode<>("C");
    MirrorTree.BinaryTreeNode<String> childD = new MirrorTree.BinaryTreeNode<>("D");
    MirrorTree.BinaryTreeNode<String> childF = new MirrorTree.BinaryTreeNode<>("F");

    root.setLeftChild(childB);
    root.setRightChild(childE);
    root.getLeftChild().setLeftChild(childC);
    root.getLeftChild().setRightChild(childD);
    root.getRightChild().setRightChild(childF);

    return root;
  }

}