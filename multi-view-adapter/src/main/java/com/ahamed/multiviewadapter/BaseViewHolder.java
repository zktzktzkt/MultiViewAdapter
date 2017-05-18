package com.ahamed.multiviewadapter;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;

public class BaseViewHolder<M> extends ViewHolder
    implements View.OnClickListener, View.OnLongClickListener {

  private M item;
  private OnItemClickListener<M> itemClickListener;
  private OnItemLongClickListener<M> itemLongClickListener;
  private ItemActionListener actionListener;

  public BaseViewHolder(View itemView) {
    super(itemView);
    itemView.setOnClickListener(this);
    itemView.setOnLongClickListener(this);
  }

  /**
   * Returns the item object bounded by this {@link BaseViewHolder}
   *
   * @return item bounded by this {@link BaseViewHolder}
   */
  public final M getItem() {
    return item;
  }

  final void setItem(M item) {
    this.item = item;
  }

  final void setItemActionListener(ItemActionListener actionListener) {
    this.actionListener = actionListener;
  }

  /**
   * Can be called by the child view holders to toggle the selection.
   *
   * <p>By default long press of the view holder will toggle the selection. If the selection has to
   * be toggled for an item in the view holder (ex: Button) this method can be called from the
   * item's click listener </p>
   */
  protected void toggleItemSelection() {
    actionListener.onItemSelectionToggled(getAdapterPosition());
  }

  /**
   * Register a callback to be invoked when this {@link BaseViewHolder} is clicked. If this {@link
   * BaseViewHolder} is not clickable, it becomes clickable.
   *
   * @param itemClickListener The callback that will run
   */
  protected final void setItemClickListener(OnItemClickListener<M> itemClickListener) {
    this.itemClickListener = itemClickListener;
  }

  /**
   * Register a callback to be invoked when this {@link BaseViewHolder} is clicked and held. If this
   * {@link BaseViewHolder} is not long clickable, it becomes long clickable.
   *
   * @param itemLongClickListener The callback that will run
   */
  protected final void setItemLongClickListener(OnItemLongClickListener<M> itemLongClickListener) {
    this.itemLongClickListener = itemLongClickListener;
  }

  @Override public final void onClick(View view) {
    if (null == itemClickListener) return;
    itemClickListener.onItemClick(view, getItem());
  }

  @Override public final boolean onLongClick(View view) {
    return null != itemLongClickListener && itemLongClickListener.onItemLongClick(view, getItem());
  }

  public final boolean isItemSelected() {
    return actionListener.isItemSelected(getAdapterPosition());
  }

  /**
   * Interface definition for a callback to be invoked when a {@link BaseViewHolder} is clicked.
   */
  public interface OnItemClickListener<M> {
    /**
     * Called when a {@link BaseViewHolder} has been clicked.
     *
     * @param view The view that was clicked.
     * @param item Item that is bounded by the {@link BaseViewHolder}
     */
    void onItemClick(View view, M item);
  }

  /**
   * Interface definition for a callback to be invoked when a {@link BaseViewHolder} has been
   * clicked and held.
   */
  public interface OnItemLongClickListener<M> {
    /**
     * Called when a {@link BaseViewHolder} has been clicked and held.
     *
     * @param view The view that was clicked and held.
     * @param item Item that is bounded by the {@link BaseViewHolder}
     * @return true if the callback consumed the long click, false otherwise.
     */
    boolean onItemLongClick(View view, M item);
  }
}