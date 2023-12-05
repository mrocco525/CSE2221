import components.stack.Stack;
import components.stack.Stack1L;

/**
 * Java class.
 */
public class AppendUndoController1 implements AppendUndoController {
    /**
     * Model object.
     */
    private final AppendUndoModel model;
    /**
     * View object.
     */
    private final AppendUndoView view;

    /**
     * Controller.
     *
     * @param model
     * @param view
     */
    public AppendUndoController1(AppendUndoModel model, AppendUndoView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void processResetEvent() {
        this.model.setInput("");
        Stack<String> temp = new Stack1L<String>();
        temp.clear();
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processAppendEvent(String input) {
        Stack<String> temp = new Stack1L<String>();
        temp = this.model.output();
        if (temp.length() == 0) {
            temp.push(input);
        } else {
            String str = temp.pop();
            temp.push(str);
            temp.push(str + input);
        }

        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processUndoEvent() {

    }

    /**
     * Updates view to display model.
     *
     * @param model
     *            the model
     * @param view
     *            the view
     */
    private static void updateViewToMatchModel(AppendUndoModel model,
            AppendUndoView view) {
        /*
         * Get model info
         */
        String input = model.input();
        Stack<String> temp = model.output();
        /*
         * Update view to reflect changes in model
         */
        view.updateInputDisplay(input);
        if (temp.length() > 0) {
            String output = temp.pop();
            view.updateOutputDisplay(output);
            temp.push(output);
        } else {
            temp.push("");
        }
    }

}
