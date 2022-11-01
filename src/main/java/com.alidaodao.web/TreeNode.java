package com.alidaodao.web;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vincent@bigonelab.com
 */
public class TreeNode<T extends TreeNode> {

    private String id;
    private String pid;
    private String name;
    private List<T> children = new ArrayList<>();
    private Boolean disabled = false;

    public TreeNode(String id, String pid, String name) {
        this.id = id;
        this.pid = pid;
        this.name = name;
    }

    public TreeNode() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<T> getChildren() {
        return children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }

    /**
     * @param list
     * @return
     */
    public static <T extends TreeNode> List<T> listToTree(List<T> list) {
        //用递归找子。
        List<T> treeList = new ArrayList<T>();
        for (T tree : list) {
            if (tree.getPid().equals("0")) {
                treeList.add(findChildren(tree, list));
            }
        }
        return treeList;
    }


    /**
    * @Description:    属性结构转List
    * @author vincent@bigonelab.com
    * @CreateDate:     2019/5/21 13:43
    * @UpdateRemark:   修改内容
    * @Version:        1.0
    */
    public static <T extends TreeNode> List<T> getTree2List(List<T> list) {
        List<T> result = new ArrayList<>();
        list.stream().forEach(iter -> {
            result.add(iter);
            if(iter.getChildren().size() > 0){
                result.addAll(getTree2List(iter.getChildren()));
            }

        });
        result.stream().forEach(iter -> iter.setChildren(null));
        return result;
    }



    /**
    * @Description:    递归查询子模块
    * @author vincent@bigonelab.com
    * @CreateDate:     2019/5/21 13:42
    * @UpdateRemark:   修改内容
    * @Version:        1.0
    */
    private static <T extends TreeNode> T findChildren(T t, List<T> list) {
        for (T node : list) {
            if (node.getPid().equals(t.getId())) {
                if (t.getChildren() == null) {
                    t.setChildren(new ArrayList<TreeNode>());
                }
                t.getChildren().add(findChildren(node, list));
            }
        }
        return t;
    }




}