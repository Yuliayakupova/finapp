import numpy as np
from sklearn.linear_model import LinearRegression

months = np.array([1, 2, 3, 4, 5, 6]).reshape(-1, 1)
expenses = np.array([350, 420, 400, 460, 500, 520])

model = LinearRegression()
model.fit(months, expenses)

next_month = np.array([[7]])
predicted = model.predict(next_month)

print(f"📈 Forecasted expenses for next month: €{predicted[0]:.2f}")